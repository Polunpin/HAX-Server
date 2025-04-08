package com.tencent.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.PaymentRecordMapper;
import com.tencent.model.PaymentRecord;
import com.tencent.request.PaymentCallBack;
import com.tencent.service.PaymentRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @author lanyiping
 * @description 针对表【user_payment_record(支付信息表)】的数据库操作Service实现
 * @createDate 2025-04-01 12:45:05
 */
@Service
public class PaymentRecordServiceImpl extends ServiceImpl<PaymentRecordMapper, PaymentRecord>
        implements PaymentRecordService {

    @Transactional
    public Boolean handlePaymentCallback(PaymentCallBack paymentCallBack) {
        // 1. 校验回调数据
        if (!"SUCCESS".equals(paymentCallBack.getReturnCode()) || !"SUCCESS".equals(paymentCallBack.getResultCode())) {
            return false; // 支付失败
        }

        // 2. 检查是否已处理过该回调
        String outTradeNo = paymentCallBack.getOutTradeNo();
        boolean exists = this.lambdaQuery().eq(PaymentRecord::getOutTradeNo, outTradeNo).exists();
        if (exists) {
            return true; // 重复的回调，直接返回成功
        }

        // 3. 处理回调
        PaymentRecord paymentRecord = new PaymentRecord();
        copyProperties(paymentCallBack, paymentRecord);
        paymentRecord.setPayInfo(JSONObject.toJSONString(paymentCallBack));

        // 4. 保存回调信息到数据库,并返回成功响应
        return this.save(paymentRecord);
    }

}





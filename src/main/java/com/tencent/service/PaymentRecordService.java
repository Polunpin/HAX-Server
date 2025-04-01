package com.tencent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.model.PaymentRecord;
import com.tencent.request.PaymentCallBack;

/**
 * @author lanyiping
 * @description 针对表【user_payment_record(支付信息表)】的数据库操作Service
 * @createDate 2025-04-01 12:45:05
 */
public interface PaymentRecordService extends IService<PaymentRecord> {

    Boolean handlePaymentCallback(PaymentCallBack paymentCallBack);
}

package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.request.PaymentCallBack;
import com.tencent.service.PaymentRecordService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户支付记录
 */
@Slf4j
@RestController
@RequestMapping("/paymentRecord")
public class PaymentRecordController {

    @Resource
    public PaymentRecordService paymentRecordService;


    /**
     * 支付回调接口
     *
     * @param paymentCallBack 支付回调信息
     * @return 处理结果
     */
    @PostMapping("/callback")
    public ApiResponse paymentCallback(@RequestBody PaymentCallBack paymentCallBack) {
        return ApiResponse.ok(paymentRecordService.handlePaymentCallback(paymentCallBack));
    }
}

package com.tencent.request;

import lombok.Data;

/**
 * 练习详情
 */
@Data
public class PaymentCallBack {

    /**
     * 返回状态码
     */
    private String returnCode;

    /**
     * 应用ID
     */
    private String appid;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 业务结果码
     */
    private String resultCode;

    /**
     * 用户标识
     */
    private String openid;

    /**
     * 是否关注公众号
     */
    private String isSubscribe;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 银行类型
     */
    private String bankType;

    /**
     * 订单总金额
     */
    private int totalFee;

    /**
     * 货币类型
     */
    private String feeType;

    /**
     * 现金支付金额
     */
    private int cashFee;

    /**
     * 支付平台交易号
     */
    private String transactionId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 支付完成时间
     */
    private String timeEnd;

    /**
     * 子应用ID
     */
    private String subAppid;

    /**
     * 子商户号
     */
    private String subMchId;

    /**
     * 子用户标识
     */
    private String subOpenid;

    /**
     * 子用户是否关注公众号
     */
    private String subIsSubscribe;


}
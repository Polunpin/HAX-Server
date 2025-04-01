package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 支付信息表
 *
 * @TableName user_payment_record
 */
@TableName(value = "user_payment_record")
@Data
public class PaymentRecord {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户标识
     */
    private String openid;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 银行类型
     */
    private String bankType;

    /**
     * 订单总金额（单位：分）
     */
    private Integer totalFee;

    /**
     * 货币类型
     */
    private String feeType;

    /**
     * 现金支付金额（单位：分）
     */
    private Integer cashFee;

    /**
     * 支付平台交易号
     */
    private String transactionId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 支付完成时间（格式：yyyyMMddHHmmSS）
     */
    private String timeEnd;

    /**
     * 支付信息
     */
    private Object payInfo;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户收货地址表
 *
 * @TableName shipping_address
 */
@TableName(value = "shipping_address")
@Data
public class ShippingAddress {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 收货人姓名
     */
    private String userName;

    /**
     * 邮编
     */
    private String postalCode;

    /**
     * 省份
     */
    private String provinceName;

    /**
     * 城市
     */
    private String cityName;

    /**
     * 区县
     */
    private String countyName;

    /**
     * 街道
     */
    private String streetName;

    /**
     * 详细地址信息（包括街道）
     */
    private String detailInfo;

    /**
     * 新选择器详细地址信息
     */
    private String detailInfoNew;

    /**
     * 国家码
     */
    private String nationalCode;

    /**
     * 手机号
     */
    private String telNumber;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
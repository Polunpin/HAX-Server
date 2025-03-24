package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表
 *
 * @TableName users
 */
@Data
@TableName(value = "users")
public class Users {

    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 微信唯一标识
     */
    private String unionId;

    /**
     * 付费阶段：0-白户，1-知识，2-月付，3-买断
     */
    private Integer payStage;

    /**
     * 用户状态：1-正常，0-禁用
     */
    private Integer status;

    /**
     * 熟练度
     */
    private Integer proficiency;

    /**
     * 金币(练习/挑战所得)
     */
    private Integer gold;

    /**
     * 驾驶里程
     */
    private BigDecimal mileage;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;
}
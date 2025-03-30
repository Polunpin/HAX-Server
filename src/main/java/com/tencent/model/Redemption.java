package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 兑换表
 *
 * @TableName user_redemption
 */
@Data
@TableName(value = "user_redemption")
public class Redemption {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户表ID，关联users表
     */
    private Long userId;

    /**
     * 奖励表ID，关联reward表
     */
    private Long rewardId;

    /**
     * 兑换时间
     */
    private Date exchangeTime;
}
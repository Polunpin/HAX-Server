package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 兑换表
 * @TableName redemption
 */
@TableName(value ="redemption")
@Data
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
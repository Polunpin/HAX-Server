package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 兑换记录表
 * @TableName redemption_record
 */
@TableName(value ="redemption_record")
@Data
public class RedemptionRecord {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学生表ID，关联users表
     */
    private Long studentId;

    /**
     * 奖励表ID，关联reward表
     */
    private Long rewardId;

    /**
     * 金币消耗
     */
    private Integer goldCost;

    /**
     * 创建时间(兑换时间)
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
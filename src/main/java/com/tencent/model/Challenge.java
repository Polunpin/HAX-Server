package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 挑战表
 * @TableName challenge
 */
@TableName(value ="challenge")
@Data
public class Challenge {
    /**
     * 挑战ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 挑战标题
     */
    private String title;

    /**
     * 挑战描述
     */
    private String description;

    /**
     * 挑战条件类型：1-次数, 2-里程, 3-综合
     */
    private Integer conditionType;

    /**
     * 状态：0-预备, 1-上架, 2-完成, 3-失效
     */
    private Integer status;

    /**
     * 条件值(次数/里程等)
     */
    private Integer conditionValue;

    /**
     * 奖励金币
     */
    private Integer rewardGold;

    /**
     * 排序字段
     */
    private Integer sortOrder;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;
}
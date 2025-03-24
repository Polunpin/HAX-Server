package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 练习表
 *
 * @TableName practice
 */
@Data
@TableName(value = "practice")
public class Practice {

    /**
     * 练习ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 练习标题
     */
    private String title;

    /**
     * 练习目标
     */
    private Object target;

    /**
     * 注意事项
     */
    private String notes;

    /**
     * 练习类型：1-基础, 2-进阶, 3-突破
     */
    private Integer type;

    /**
     * 奖励金币
     */
    private Integer rewardGold;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;
}
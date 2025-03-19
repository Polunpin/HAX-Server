package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 奖励表
 * @TableName reward
 */
@TableName(value ="reward")
@Data
public class Reward {
    /**
     * 奖励ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 奖励标题
     */
    private String title;

    /**
     * 奖励描述
     */
    private String description;

    /**
     * 奖励图片
     */
    private String image;

    /**
     * 兑换条件(如所需金币数)
     */
    private Integer exchangeCondition;

    /**
     * 奖励类型：1-实物,2-虚拟道具,3-优惠券
     */
    private Integer rewardType;

    /**
     * 排序字段
     */
    private Integer sortOrder;

    /**
     * 有效性：1-上架,0-下架
     */
    private Integer isActive;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;
}
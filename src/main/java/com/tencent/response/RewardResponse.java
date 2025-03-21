package com.tencent.response;

import lombok.Data;

/**
 * 奖励列表
 */
@Data
public class RewardResponse {

    /**
     * 奖励ID
     */
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
     * 是否兑换(ture:已兑换,false:未兑换)
     */
    private Boolean isExchange;

}
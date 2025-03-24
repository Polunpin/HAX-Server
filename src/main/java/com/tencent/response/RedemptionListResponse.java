package com.tencent.response;

import lombok.Data;

/**
 * 奖励兑换列表
 */
@Data
public class RedemptionListResponse {

    /**
     * 兑换ID
     */
    private Long id;

    /**
     * 奖励标题
     */
    private String title;

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
     * 兑换时间
     */
    private String exchangeTime;

}
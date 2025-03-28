package com.tencent.request;

import lombok.Data;

/**
 * 奖励兑换保存
 */
@Data
public class RedemptionRequest {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 奖励ID
     */
    private Long rewardId;

    /**
     * 金币消耗
     */
    private Long goldCoins;

}

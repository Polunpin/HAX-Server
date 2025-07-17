package com.tencent.request;

import com.tencent.model.ShippingAddress;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 奖励兑换保存
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RedemptionRequest extends ShippingAddress {

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

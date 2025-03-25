package com.tencent.response;

import lombok.Data;

import java.util.List;

/**
 * 奖励列表
 */
@Data
public class RewardResponse {
    
    /**
     * 当前金币
     */
    private Integer gold;

    /**
     * 奖励列表
     */
    private List<RewardsResponse> rewards;
}
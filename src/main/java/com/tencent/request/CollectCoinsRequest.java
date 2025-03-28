package com.tencent.request;

import lombok.Data;

/**
 * 领取金币入参
 */
@Data
public class CollectCoinsRequest {

    /**
     * 挑战ID,关联challenge表
     */
    private Long challengeId;

    /**
     * 用户ID,关联users表
     */
    private Long userId;

    /**
     * 当前用户金币
     */
    private Integer currentGold;

    /**
     * 当前挑战任务-奖励金币
     */
    private Integer taskGold;

}
package com.tencent.response;

import com.tencent.model.Challenge;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 挑战表
 */
@Data
public class ChallengeResponse {

    /**
     * 当前金币
     */
    private Integer gold;

    /**
     * 已练习里程
     */
    private BigDecimal distance;

    /**
     * 挑战列表
     */
    private List<Challenge> challenges;

}
package com.tencent.response;

import com.tencent.model.Challenge;
import lombok.Data;

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
     * 挑战列表
     */
    private List<ChallengesResponse> challenges;

}
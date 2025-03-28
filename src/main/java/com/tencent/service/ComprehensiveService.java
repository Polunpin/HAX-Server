package com.tencent.service;

import com.tencent.config.ApiResponse;
import com.tencent.request.CollectCoinsRequest;
import com.tencent.request.PracticeRequest;
import com.tencent.request.RedemptionRequest;
import com.tencent.response.ChallengeResponse;
import com.tencent.response.PracticeRecordInfoResponse;
import com.tencent.response.RewardResponse;

/**
 * @author lanyiping
 * @description 综合：混合其他类的接口
 */
public interface ComprehensiveService {

    PracticeRecordInfoResponse getPracticeRecord(String id);

    ChallengeResponse getChallengeList(String userId);

    ApiResponse exchange(RedemptionRequest redemptionRequest);

    RewardResponse getRewardList(String userId);

    Object getPracticeDetail(PracticeRequest practiceRequest);

    Object getPracticeList(String userId);

    Boolean collectCoins(CollectCoinsRequest collectCoins);

    Object exchangeDetail(String redemptionId);
}

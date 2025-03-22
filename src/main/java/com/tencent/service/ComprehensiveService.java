package com.tencent.service;

import com.tencent.response.*;

import java.util.List;

/**
 * @author lanyiping
 * @description 综合：混合其他类的接口
 */
public interface ComprehensiveService {

    UserResponse continueLearning(String userId);

    List<PracticeResponse> getPracticeList(String userId);

    List<PracticeRecordsResponse> getPracticeRecordList(String userId);

    //奖励列表全部内容
    List<RewardResponse> getRewardList(String userId);

    //奖励列表中已兑换的内容
    List<RewardResponse> getRewardExchangeList(String userId);

    ChallengeResponse getChallengeList(String userId);
}

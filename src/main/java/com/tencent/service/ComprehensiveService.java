package com.tencent.service;

import com.tencent.model.Practice;
import com.tencent.response.PracticeRecordsResponse;
import com.tencent.response.RewardResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 综合：混合其他类的接口
 */
public interface ComprehensiveService {

    List<Practice> getPracticeList();

    List<PracticeRecordsResponse> getPracticeRecordList(String userId);

    //奖励列表全部内容
    List<RewardResponse> getRewardList(String userId);

    //奖励列表中已兑换的内容
    List<RewardResponse> getRewardExchangeList(String userId);
}

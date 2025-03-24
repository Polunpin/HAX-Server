package com.tencent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.model.Reward;
import com.tencent.response.RewardResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【reward(奖励表)】的数据库操作Service
 * @createDate 2025-03-19 22:06:35
 */
public interface RewardService extends IService<Reward> {

    Reward getRewardDetail(String id);

    List<RewardResponse> getRewardList(String userId);
}

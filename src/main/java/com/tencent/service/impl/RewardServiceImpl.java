package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.RewardMapper;
import com.tencent.model.Reward;
import com.tencent.response.RewardsResponse;
import com.tencent.service.RewardService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【reward(奖励表)】的数据库操作Service实现
 * @createDate 2025-03-19 22:06:35
 */
@Service
public class RewardServiceImpl extends ServiceImpl<RewardMapper, Reward>
        implements RewardService {

    @Resource
    public RewardMapper rewardMapper;

    @Override
    public Reward getRewardDetail(String id) {
        return this.getById(id);
    }

    @Override
    public List<RewardsResponse> getRewardList(String userId) {
        return rewardMapper.getRewardList(userId);
    }
}





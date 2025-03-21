package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.RewardMapper;
import com.tencent.model.Reward;
import com.tencent.service.RewardService;
import org.springframework.stereotype.Service;

/**
* @author lanyiping
* @description 针对表【reward(奖励表)】的数据库操作Service实现
* @createDate 2025-03-19 22:06:35
*/
@Service
public class RewardServiceImpl extends ServiceImpl<RewardMapper, Reward>
    implements RewardService{

    @Override
    public Reward getRewardDetail(Long id) {
        return this.getById(id);
    }
}





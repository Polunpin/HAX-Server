package com.tencent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.model.Reward;
import com.tencent.response.RewardResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【reward(奖励表)】的数据库操作Mapper
 * @createDate 2025-03-19 22:06:35
 * @Entity com.tencent.model.Reward
 */
public interface RewardMapper extends BaseMapper<Reward> {

    List<RewardResponse> getRewardList(String userId);
}





package com.tencent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.model.Challenge;
import com.tencent.response.ChallengesResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【challenge(挑战表)】的数据库操作Mapper
 * @createDate 2025-03-20 01:23:53
 * @Entity com.tencent.model.Challenge
 */
public interface ChallengeMapper extends BaseMapper<Challenge> {

    List<ChallengesResponse> getChallengeList(String userId);
}





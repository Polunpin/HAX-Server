package com.tencent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.model.Challenge;
import com.tencent.response.ChallengesResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【challenge(挑战表)】的数据库操作Service
 * @createDate 2025-03-19 22:02:52
 */
public interface ChallengeService extends IService<Challenge> {

    List<ChallengesResponse> getChallengeList(String userId);
}

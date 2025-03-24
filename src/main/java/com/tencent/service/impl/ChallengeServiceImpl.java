package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.ChallengeMapper;
import com.tencent.model.Challenge;
import com.tencent.response.ChallengesResponse;
import com.tencent.service.ChallengeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【challenge(挑战表)】的数据库操作Service实现
 * @createDate 2025-03-19 22:02:52
 */
@Service
public class ChallengeServiceImpl extends ServiceImpl<ChallengeMapper, Challenge>
        implements ChallengeService {

    @Resource
    public ChallengeMapper challengeMapper;

    @Override
    public List<ChallengesResponse> getChallengeList(String userId) {
        return challengeMapper.getChallengeList(userId);
    }
}





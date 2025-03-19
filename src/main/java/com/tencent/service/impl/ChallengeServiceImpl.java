package com.tencent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.model.*;
import com.tencent.response.ChallengeResponse;
import com.tencent.service.ChallengeService;
import com.tencent.mapper.ChallengeMapper;
import com.tencent.service.PracticeRecordService;
import com.tencent.service.UsersService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
* @author lanyiping
* @description 针对表【challenge(挑战表)】的数据库操作Service实现
* @createDate 2025-03-19 22:02:52
*/
@Service
public class ChallengeServiceImpl extends ServiceImpl<ChallengeMapper, Challenge>
    implements ChallengeService{

    @Resource
    public UsersService usersService;
    @Resource
    public PracticeRecordService practiceRecordService;


    @Override
    public ChallengeResponse getChallengeList(String userId) {
        var challengeResponse = new ChallengeResponse();

        //根据用户id查询当前金币
        Users user = usersService.getById(userId);
        challengeResponse.setGold(user.getGold());

        //根据用户id查询练习记录总里程
        QueryWrapper<PracticeRecord> queryWrapper = Wrappers.query();
        queryWrapper.select("SUM(distance) as distance");
        BigDecimal distance = practiceRecordService.getOne(queryWrapper).getDistance();
        challengeResponse.setDistance(distance);

        //查询挑战列表
        QueryWrapper<Challenge> challengeQuery = Wrappers.query();
        challengeQuery.eq("user_id",userId);
        List<Challenge> list = this.list(challengeQuery);
        challengeResponse.setChallenges(list);
        return challengeResponse;
    }
}





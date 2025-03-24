package com.tencent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.model.Challenge;
import com.tencent.model.Users;
import com.tencent.response.ChallengeResponse;
import com.tencent.response.ChallengesResponse;
import com.tencent.service.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @author lanyiping
 * @description 综合类：混合其他类的接口
 */
@Service
public class ComprehensiveServiceImpl implements ComprehensiveService {

    @Resource
    public UsersService users;
    @Resource
    public PracticeService practice;
    @Resource
    public PracticeRecordService practiceRecord;
    @Resource
    public ChallengeService challenge;

    /**
     * @param targetMileage 挑战任务里程数
     * @param totalMileage  当前驾驶里程总数
     * @return 进度
     */
    public static String calculatePercentageOrOne(Integer targetMileage, Integer totalMileage) {
        BigDecimal bigDecimal_a = BigDecimal.valueOf(targetMileage);
        BigDecimal bigDecimal_b = BigDecimal.valueOf(totalMileage);
        // 如果 B 为 0，直接返回 "0%"（避免除以 0 的问题）
        if (bigDecimal_b.compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }

        //当前驾驶里程总数| 计算 A/B,且四舍五入
        BigDecimal ratio = bigDecimal_a.divide(bigDecimal_b, 4, RoundingMode.HALF_UP); // 保留 4 位小数进行计算

        // 判断逻辑
        if (ratio.compareTo(BigDecimal.ONE) < 0) {
            // A/B 小于 1，返回百分比
            BigDecimal percentage = ratio.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP); // 转换为百分比，保留 2 位小数
            return percentage.toString();
        } else {
            // A/B 大于等于 1，返回 "1"
            return "1";
        }
    }

    @Override
    public ChallengeResponse getChallengeList(String userId) {
        var challengeResponse = new ChallengeResponse();

        //根据用户id查询当前金币
        Users user = users.getById(userId);
        challengeResponse.setGold(user.getGold());

        //查询挑战列表
        QueryWrapper<Challenge> challengeQuery = new QueryWrapper<>();
        List<ChallengesResponse> list = challenge.list(challengeQuery)
                .stream()
                .map(challenge -> {
                    ChallengesResponse response = new ChallengesResponse();
                    copyProperties(challenge, response);
                    response.setProgress(calculatePercentageOrOne(challenge.getConditionValue(), user.getMileage()));
                    return response;
                })
                .toList();
        challengeResponse.setChallenges(list);
        return challengeResponse;
    }

}





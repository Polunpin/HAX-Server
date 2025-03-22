package com.tencent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.model.*;
import com.tencent.response.*;
import com.tencent.service.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @author lanyiping
 * @description 综合类：混合其他类的接口
 */
@Service
public class ComprehensiveServiceImpl implements ComprehensiveService {

    @Resource
    public UsersService usersService;
    @Resource
    public LearningProgressService learningProgressService;
    @Resource
    public PracticeService practiceService;
    @Resource
    public PracticeRecordService practiceRecordService;
    @Resource
    public ChallengeService challengeService;
    @Resource
    public RewardService rewardService;
    @Resource
    public RedemptionService redemptionService;


    @Override
    public UserResponse continueLearning(String userId) {
        UserResponse userResponse = new UserResponse();
        Users users = usersService.getById(userId);

        QueryWrapper<LearningProgress> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("updated_at") // 按 updated_at 降序排序
                .last("LIMIT 1"); // 取第一条记录
        LearningProgress latestLearningProgress = learningProgressService.getOne(queryWrapper);
        copyProperties(users, userResponse);
        userResponse.setContinueLearning(latestLearningProgress);
        return userResponse;
    }

    @Override
    public List<PracticeResponse> getPracticeList(String userId) {
        //获取练习列表
        List<PracticeResponse> practiceRecords = practiceService.list()
                .stream()
                .map(record -> {
                    PracticeResponse response = new PracticeResponse();
                    copyProperties(record, response);
                    return response;
                })
                .toList();
        //获取练习记录，并匹配练习列表，计算练习时长
        practiceRecords.forEach(item -> {
            //获取当前练习总耗时
            item.setDuration(practiceRecordService.durationSum(userId, item.getId()));
        });
        return practiceRecords;
    }

    @Override
    public List<PracticeRecordsResponse> getPracticeRecordList(String userId) {
        //列表查询并复制给出参对象
        List<PracticeRecordsResponse> practiceRecords = practiceRecordService
                .getPracticeRecordList(userId)
                .stream()
                .map(record -> {
                    PracticeRecordsResponse response = new PracticeRecordsResponse();
                    copyProperties(record, response);
                    return response;
                })
                .toList();
        for (PracticeRecordsResponse practiceRecord : practiceRecords) {
            practiceRecord.setTitle(practiceService.getById(practiceRecord.getPracticeId()).getTitle());
        }
        return practiceRecords;
    }


    @Override
    public List<RewardResponse> getRewardList(String userId) {
        List<RewardResponse> list = new ArrayList<>();

        // 查询所有奖励列表
        List<Reward> rewards = rewardService.list();

        // 查询用户已兑换的奖励列表
        List<Redemption> rewardExchangeList = redemptionService.getRewardExchangeList(userId);

        // 将已兑换的 rewardId 提取为集合
        Set<Long> exchangedRewardIds = rewardExchangeList.stream()
                .map(Redemption::getRewardId)
                .collect(Collectors.toSet());

        // 遍历所有奖励列表，构建 RewardResponse
        for (Reward reward : rewards) {
            RewardResponse response = new RewardResponse();
            copyProperties(reward, response); // 复制属性
            response.setIsExchange(exchangedRewardIds.contains(reward.getId())); // 设置是否已兑换
            list.add(response);
        }
        return list;
    }


    @Override
    public List<RewardResponse> getRewardExchangeList(String userId) {
        QueryWrapper<Reward> queryWrapper = new QueryWrapper<>();
        // 关联查询：通过 redemption 表的 userId 和 Reward 表的 rewardId 进行关联
        queryWrapper.inSql("id", "SELECT reward_id FROM redemption WHERE user_id = '" + userId + "'");
        //列表查询并复制给出参对象
        return rewardService.list(queryWrapper)
                .stream()
                .map(record -> {
                    RewardResponse response = new RewardResponse();
                    copyProperties(record, response);
                    return response;
                })
                .toList();
    }

    @Override
    public ChallengeResponse getChallengeList(String userId) {
        var challengeResponse = new ChallengeResponse();

        //根据用户id查询当前金币
        Users user = usersService.getById(userId);
        challengeResponse.setGold(user.getGold());

        //查询挑战列表
        QueryWrapper<Challenge> challengeQuery = new QueryWrapper<>();
        List<ChallengesResponse> list = challengeService.list(challengeQuery)
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

    /**
     *
     * @param targetMileage 挑战任务里程数
     * @param totalMileage 当前驾驶里程总数
     * @return 进度
     */
    public static String calculatePercentageOrOne(Integer targetMileage, Integer totalMileage) {
        BigDecimal bigDecimal_a = BigDecimal.valueOf(targetMileage);
        BigDecimal bigDecimal_b = BigDecimal.valueOf(totalMileage);
        // 如果 B 为 0，直接返回 "0%"（避免除以 0 的问题）
        if (bigDecimal_b.compareTo(BigDecimal.ZERO) == 0) {
            return "0%";
        }

        //当前驾驶里程总数| 计算 A/B,且四舍五入
        BigDecimal ratio = bigDecimal_a.divide(bigDecimal_b, 4, RoundingMode.HALF_UP); // 保留 4 位小数进行计算

        // 判断逻辑
        if (ratio.compareTo(BigDecimal.ONE) < 0) {
            // A/B 小于 1，返回百分比
            BigDecimal percentage = ratio.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP); // 转换为百分比，保留 2 位小数
            return percentage + "%";
        } else {
            // A/B 大于等于 1，返回 "1"
            return "1";
        }
    }

}





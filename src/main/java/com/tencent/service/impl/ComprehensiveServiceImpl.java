package com.tencent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.model.*;
import com.tencent.response.PracticeRecordsResponse;
import com.tencent.response.RewardResponse;
import com.tencent.response.UserResponse;
import com.tencent.service.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
    public List<Practice> getPracticeList() {
        List<Practice> practiceList = practiceService.getPracticeList();
        practiceList.forEach(item -> {
            //获取当前练习总耗时
            item.setDuration(practiceRecordService.durationSum(item.getId()));
        });
        return practiceList;
    }

    @Override
    public List<PracticeRecordsResponse> getPracticeRecordList(String userId) {
        List<PracticeRecordsResponse> practiceRecords = new ArrayList<>();
        List<PracticeRecord> practiceRecordList = practiceRecordService.getPracticeRecordList(userId);
        copyProperties(practiceRecordList, practiceRecords);
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
        List<RewardResponse> list = new ArrayList<>();

        QueryWrapper<Reward> queryWrapper = new QueryWrapper<>();
        // 关联查询：通过 redemption 表的 userId 和 Reward 表的 rewardId 进行关联
        queryWrapper.inSql("id", "SELECT reward_id FROM redemption WHERE user_id = '" + userId + "'");
        List<Reward> rewards = rewardService.list(queryWrapper);
        copyProperties(rewards, list);
        return list;
    }

}





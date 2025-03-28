package com.tencent.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.tencent.config.ApiResponse;
import com.tencent.model.Practice;
import com.tencent.model.PracticeRecord;
import com.tencent.model.Reward;
import com.tencent.model.Users;
import com.tencent.request.PracticeRequest;
import com.tencent.request.RedemptionRequest;
import com.tencent.response.*;
import com.tencent.service.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
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
    public PracticeService practiceS;
    @Resource
    public PracticeRecordService practiceRecordS;
    @Resource
    public ChallengeService challenge;
    @Resource
    public RewardService reward;
    @Resource
    public RedemptionService redemption;

    /**
     * @param targetMileage  挑战目标
     * @param currentMileage 当前驾驶里程
     * @return 进度
     */
    public static String progress(Integer targetMileage, BigDecimal currentMileage) {
        //当前驾驶里程为0
        if (currentMileage.compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }

        BigDecimal targetMileageBig = BigDecimal.valueOf(targetMileage);
        //进度｜计算 currentMileage/targetMileage(当前驾驶里程/挑战目标),且四舍五入
        BigDecimal ratio = currentMileage.divide(targetMileageBig, 4, RoundingMode.HALF_UP);
        //ratio比“1”小
        if (ratio.compareTo(BigDecimal.ONE) < 0) {
            //当progress不等于100时，进度条为黑色
            return ratio.multiply(BigDecimal.valueOf(100))
                    .setScale(0, RoundingMode.HALF_UP)
                    .toPlainString();
        } else {
            //当progress为100时，进度条为绿色
            return "100";
        }
    }

    @Override
    public PracticeRecordInfoResponse getPracticeRecord(String id) {
        PracticeRecord practiceRecord = practiceRecordS.getById(id);
        PracticeRecordInfoResponse practiceRecordInfo = new PracticeRecordInfoResponse();
        copyProperties(practiceRecord, practiceRecordInfo);
        var practice = practiceS.getById(practiceRecord.getPracticeId());

        // 定义格式化模板(时间)
        SimpleDateFormat startFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat endFormatter = new SimpleDateFormat("HH:mm");
        practiceRecordInfo.setConsumptionTime(startFormatter
                .format(practiceRecord.getStartTime()) + "-" + endFormatter.format(practiceRecord.getEndTime()));

        practiceRecordInfo.setTitle(practice.getTitle());
        practiceRecordInfo.setPerformance(JSON.parseArray(practiceRecord.getPerformance()));
        practiceRecordInfo.setTrajectory(JSON.parseArray(practiceRecord.getTrajectory()));
        return practiceRecordInfo;
    }

    @Override
    public ChallengeResponse getChallengeList(String userId) {
        var challengeResponse = new ChallengeResponse();

        //根据用户id查询当前金币
        Users user = users.getById(userId);
        challengeResponse.setGold(user.getGold());
        List<ChallengesResponse> challengeList = challenge.getChallengeList(userId);
        challengeList.forEach(challenges ->
                challenges.setProgress(progress(challenges.getConditionValue(), user.getMileage())));
        //查询挑战列表
        challengeResponse.setChallenges(challengeList);
        return challengeResponse;
    }


    @Override
    public RewardResponse getRewardList(String userId) {
        RewardResponse rewardResponse = new RewardResponse();
        //根据用户id查询当前金币
        Users user = users.getById(userId);
        rewardResponse.setGold(user.getGold());
        rewardResponse.setRewards(reward.getRewardList(userId));
        //查询挑战列表
        return rewardResponse;
    }

    @Override
    public Object getPracticeDetail(PracticeRequest practiceRequest) {
        var practiceResponse = new PracticeResponse();
        Practice practice = practiceS.getById(practiceRequest.getPracticeId());
        copyProperties(practice, practiceResponse);
        List<PracticeRecordServiceImpl.ContentStatistics> practiceProgress = practiceRecordS.getPracticeProgress(practiceRequest);
        practiceResponse.setTarget(JSON.parseArray(JSONObject.toJSONString(practiceProgress)));
        practiceResponse.setNotes(JSON.parseArray(practice.getNotes()));
        return practiceResponse;
    }

    @Override
    public List<PracticeResponse> getPracticeList(String userId) {
        List<PracticeResponse> practiceList = practiceS.getPracticeList(userId);
        practiceList.forEach(practice -> {
            PracticeRequest practiceRequest = new PracticeRequest();
            practiceRequest.setPracticeId(practice.getId());
            practiceRequest.setUserId(userId);
            List<PracticeRecordServiceImpl.ContentStatistics> practiceProgress = practiceRecordS.getPracticeProgress(practiceRequest);
            practice.setTarget(JSON.parseArray(JSONObject.toJSONString(practiceProgress)));
//            practice.setTarget(JSON.parseArray(String.valueOf(practiceProgress)));
        });
        return practiceList;
    }

    @Override
    public ApiResponse exchange(RedemptionRequest redemptionRequest) {
        final String INSUFFICIENT_GOLD_MESSAGE = "金币不足";
        final String SUCCESS_MESSAGE = "兑换成功";
        final String FAILURE_MESSAGE = "金币被风吹跑了，快去联系客服";

        if (!isGoldSufficient(redemptionRequest)) {
            return ApiResponse.ok(INSUFFICIENT_GOLD_MESSAGE, false);
        }
        if (redemption.exchange(redemptionRequest)) {
            return ApiResponse.ok(SUCCESS_MESSAGE, true);
        } else {
            return ApiResponse.ok(FAILURE_MESSAGE, false);
        }
    }

    private boolean isGoldSufficient(RedemptionRequest redemptionRequest) {
        Users userInfo = users.getById(redemptionRequest.getUserId());
        Reward rewardById = reward.getById(redemptionRequest.getRewardId());
        return userInfo.getGold() >= rewardById.getExchangeCondition();
    }

}





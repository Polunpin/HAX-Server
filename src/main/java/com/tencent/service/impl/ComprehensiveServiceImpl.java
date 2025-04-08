package com.tencent.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tencent.config.ApiResponse;
import com.tencent.mapper.PracticeMapper;
import com.tencent.model.*;
import com.tencent.request.CollectCoinsRequest;
import com.tencent.request.PracticeRequest;
import com.tencent.request.RedemptionRequest;
import com.tencent.response.*;
import com.tencent.service.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @author lanyiping
 * @description 综合类：混合其他类的接口
 */
@Slf4j
@Service
public class ComprehensiveServiceImpl implements ComprehensiveService {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    @Resource
    public UsersService usersS;
    @Resource
    public PracticeService practiceS;
    @Resource
    public PracticeRecordService practiceRecordS;
    // TODO 代码逻辑优化
    @Resource
    public PracticeMapper practiceMapper;
    @Resource
    public ChallengeService challengeS;
    @Resource
    public RewardService reward;
    @Resource
    public RedemptionService user_redemptionS;
    @Resource
    public ChallengeRecordService challengeRecordS;

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
        Users user = usersS.getById(userId);
        challengeResponse.setGold(user.getGold());
        List<ChallengesResponse> challengeList = challengeS.getChallengeList(userId);
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
        Users user = usersS.getById(userId);
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
        List<PracticeResponse> practiceList = practiceMapper.getPracticeList(userId);
        practiceList.forEach(practice -> {
            PracticeRequest practiceRequest = new PracticeRequest();
            practiceRequest.setPracticeId(practice.getId());
            practiceRequest.setUserId(userId);
            List<PracticeRecordServiceImpl.ContentStatistics> practiceProgress = practiceRecordS.getPracticeProgress(practiceRequest);
            practice.setNotes(JSON.parseArray(String.valueOf(practice.getNotes())));
            if (practiceProgress.isEmpty()) {
                practice.setTarget(JSON.parseArray(String.valueOf(practice.getTarget())));
            } else {
                practice.setTarget(JSON.parseArray(JSONObject.toJSONString(practiceProgress)));
            }
        });
        return practiceList;
    }

    @Override
    public Boolean collectCoins(CollectCoinsRequest collectCoins) {
        // 更新用户金币数量
        UpdateWrapper<Users> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", collectCoins.getUserId());
        updateWrapper.setSql("gold = gold + " + collectCoins.getTaskGold());

        boolean isUpdated = usersS.update(updateWrapper);
        if (!isUpdated) {
            return false; // 如果用户金币更新失败，直接返回
        }

        ChallengeRecord challengeRecord = new ChallengeRecord();
        challengeRecord.setUserId(collectCoins.getUserId());
        challengeRecord.setChallengeId(collectCoins.getChallengeId());
        // 保存挑战记录
        return challengeRecordS.save(challengeRecord);
    }

    @Override
    public Object exchangeDetail(String user_redemptionId) {
        Redemption Redemption = user_redemptionS.getById(user_redemptionId);
        if (Redemption != null) {
            return reward.getById(Redemption.getRewardId());
        }
        return null;
    }

    @Override
    public ApiResponse exchange(RedemptionRequest RedemptionRequest) {
        final String INSUFFICIENT_GOLD_MESSAGE = "金币不足";
        final String SUCCESS_MESSAGE = "兑换成功";
        final String FAILURE_MESSAGE = "金币被风吹跑了，快去联系客服";

        if (!isGoldSufficient(RedemptionRequest)) {
            return ApiResponse.ok(INSUFFICIENT_GOLD_MESSAGE, false);
        }

        if (user_redemptionS.exchange(RedemptionRequest)) {
            UpdateWrapper<Users> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", RedemptionRequest.getUserId());
            updateWrapper.setSql("gold = gold - " + RedemptionRequest.getGoldCoins());
            usersS.update(updateWrapper);
            return ApiResponse.ok(SUCCESS_MESSAGE, true);
        } else {
            return ApiResponse.ok(FAILURE_MESSAGE, false);
        }
    }

    private boolean isGoldSufficient(RedemptionRequest RedemptionRequest) {
        Users userInfo = usersS.getById(RedemptionRequest.getUserId());
        Reward rewardById = reward.getById(RedemptionRequest.getRewardId());
        return userInfo.getGold() >= rewardById.getExchangeCondition();
    }

    @Override
    public Object pay(HttpServletRequest request, Long amount) {
        String outTradeNo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +
                "-HuAnXing-" + UUID.randomUUID().toString().substring(0, 8);

        JSONObject payment = new JSONObject()
                .fluentPut("openid", request.getHeader("X-WX-OPENID"))
                .fluentPut("body", "护安行小程序会员付")
                .fluentPut("out_trade_no", outTradeNo)
                .fluentPut("spbill_create_ip", request.getHeader("X-Original-Forwarded-For"))
                .fluentPut("env_id", request.getHeader("X-WX-ENV"))
                .fluentPut("total_fee", amount)
                .fluentPut("callback_type", 2)
                .fluentPut("sub_mch_id", "1683694889")
                .fluentPut("container", new JSONObject()
                        .fluentPut("service", "huanx-server")
                        .fluentPut("path", "/paymentRecord/callback"));

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.weixin.qq.com/_/pay/unifiedorder"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payment.toJSONString()))
                .timeout(Duration.ofSeconds(10))
                .build();

        try {
            HttpResponse<String> response = CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return JSON.parseObject(response.body())
                    .getJSONObject("respdata")
                    .getJSONObject("payment");
        } catch (IOException | InterruptedException e) {
            // 根据业务需求处理异常
            throw new RuntimeException("支付请求失败", e);
        }
    }
}





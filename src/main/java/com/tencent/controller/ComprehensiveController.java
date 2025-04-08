package com.tencent.controller;


import com.tencent.config.ApiResponse;
import com.tencent.request.CollectCoinsRequest;
import com.tencent.request.PracticeRequest;
import com.tencent.request.RedemptionRequest;
import com.tencent.service.ComprehensiveService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 综合类：混合其他类的接口
 */
@Slf4j
@RestController
@RequestMapping("/comprehensive")
public class ComprehensiveController {

    @Resource
    public ComprehensiveService comprehensiveService;


    /**
     * 功能：练习详情查询
     * 界面：练习详情页
     *
     * @param practiceRequest 练习详情入参
     * @return 练习详情信息
     */
    @GetMapping("/getPracticeDetail")
    public ApiResponse getPracticeDetail(PracticeRequest practiceRequest) {
        return ApiResponse.ok(comprehensiveService.getPracticeDetail(practiceRequest));
    }

    /**
     * 功能：练习列表查询
     * 界面：练习列表
     * 功能完整度：2/2
     *
     * @param userId 用户ID
     * @return 所有练习的列表
     */
    @GetMapping("/getPracticeList")
    public ApiResponse getPracticeList(String userId) {
        return ApiResponse.ok(comprehensiveService.getPracticeList(userId));
    }

    /**
     * 功能：查询练习记录详情
     * 界面：练习报告、练习记录列表详情页
     *
     * @param id 练习记录ID
     * @return 练习详情
     */
    @GetMapping("getPracticeRecord")
    public ApiResponse getPracticeRecord(String id) {
        return ApiResponse.ok(comprehensiveService.getPracticeRecord(id));
    }

    /**
     * 功能：挑战列表查询
     * 界面：挑战列表
     *
     * @param userId 用户ID
     * @return 挑战列表
     */
    @GetMapping("/getChallengeList")
    public ApiResponse getChallengeList(String userId) {
        return ApiResponse.ok(comprehensiveService.getChallengeList(userId));
    }


    /**
     * 功能：查询未被兑换的奖励列表
     * 界面：奖励列表
     *
     * @return 奖励列表
     */
    @GetMapping("/getRewardList")
    public ApiResponse getRewardList(String userId) {
        return ApiResponse.ok(comprehensiveService.getRewardList(userId));
    }

    /**
     * 功能：领取金币
     * 界面：挑战列表(完成挑战任务，领取金币)
     *
     * @param collectCoins 领取金币
     * @return Boolean
     */
    @PostMapping("/collectCoins")
    public ApiResponse collectCoins(@RequestBody CollectCoinsRequest collectCoins) {
        return ApiResponse.ok(comprehensiveService.collectCoins(collectCoins));
    }

    /**
     * 功能：奖励兑换
     * 界面：奖励详情的保存
     *
     * @param redemptionRequest 奖励兑换保存
     * @return Boolean
     */
    @PostMapping("/exchange")
    public ApiResponse exchange(@RequestBody RedemptionRequest redemptionRequest) {
        return comprehensiveService.exchange(redemptionRequest);
    }

    /**
     * 功能：兑换详情
     * 界面：兑换详情
     *
     * @param redemptionId 兑换ID
     * @return 兑换详情
     */
    @GetMapping("/exchangeDetail")
    public ApiResponse exchangeDetail(String redemptionId) {
        return ApiResponse.ok(comprehensiveService.exchangeDetail(redemptionId));
    }

    /**
     * 功能：支付
     * 界面：支付
     *
     * @param request 请求体
     * @param amount  支付金额
     * @return 兑换详情
     */
    @GetMapping("/pay")
    public ApiResponse pay(HttpServletRequest request, String amount) {
        log.info("pay: {}", amount);
        return ApiResponse.ok(comprehensiveService.pay(request, amount));
    }
}

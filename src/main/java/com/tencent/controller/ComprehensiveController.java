package com.tencent.controller;


import com.tencent.config.ApiResponse;
import com.tencent.request.RedemptionRequest;
import com.tencent.service.ComprehensiveService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 综合类：混合其他类的接口
 */
@RestController
@RequestMapping("/comprehensive")
public class ComprehensiveController {

    @Resource
    public ComprehensiveService comprehensiveService;

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

}

package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.service.RewardService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 奖励
 */
@RestController
@RequestMapping("/reward")
public class RewardController {

    @Resource
    public RewardService rewardService;

    /**
     * 功能：奖励详情查询
     * 界面：奖励详情页
     *
     * @param id 奖励详情ID
     * @return 奖励详情页
     */
    @GetMapping("/getRewardDetail")
    public ApiResponse getRewardDetail(String id) {
        return ApiResponse.ok(rewardService.getRewardDetail(id));
    }

    /**
     * 功能：查询未被兑换的奖励列表
     * 界面：奖励列表
     *
     * @return 奖励列表
     */
    @GetMapping("/getRewardList")
    public ApiResponse getRewardList(String userId) {
        return ApiResponse.ok(rewardService.getRewardList(userId));
    }

}

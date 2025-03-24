package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.service.RedemptionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 兑换记录
 */
@RestController
@RequestMapping("/redemption")
public class RedemptionController {

    @Resource
    public RedemptionService redemptionService;

    /**
     * 功能：查询已被兑换的奖励列表
     * 界面：兑换记录
     *
     * @param userId 用户ID
     * @return 兑换列表
     */
    @GetMapping("/getRewardExchangeList")
    public ApiResponse getRewardExchangeList(String userId) {
        return ApiResponse.ok(redemptionService.getRewardExchangeList(userId));
    }
}

package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.request.RedemptionRequest;
import com.tencent.service.RedemptionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 功能：奖励兑换
     * 界面：奖励详情的保存
     *
     * @param redemptionRequest 奖励兑换保存
     * @return Boolean
     */
    @GetMapping("/exchange")
    public ApiResponse exchange(@RequestBody RedemptionRequest redemptionRequest) {
        return ApiResponse.ok(redemptionService.exchange(redemptionRequest));
    }

    /**
     * 功能：奖励兑换记录
     * 界面：奖励兑换
     * 功能完整度：1/2
     *
     * @param userId 用户ID
     * @return 兑换列表
     */
    @GetMapping("/getRewardExchangeList")
    public ApiResponse getRewardExchangeList(String userId) {
        return ApiResponse.ok(redemptionService.getRewardExchangeList(userId));
    }
}

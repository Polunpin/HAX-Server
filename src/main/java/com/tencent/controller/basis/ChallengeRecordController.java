package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.model.ChallengeRecord;
import com.tencent.service.ChallengeRecordService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 挑战记录
 */
@RestController()
@RequestMapping("/challengeRecord")
public class ChallengeRecordController {

    @Resource
    public ChallengeRecordService challengeRecordService;

    /**
     * 功能：领取金币
     * 界面：挑战列表(完成挑战任务，领取金币)
     *
     * @param challengeRecord 挑战记录对象
     * @return Boolean
     */
    @PostMapping("/collectCoins")
    public ApiResponse collectCoins(@RequestBody ChallengeRecord challengeRecord) {
        return ApiResponse.ok(challengeRecordService.collectCoins(challengeRecord));
    }


}

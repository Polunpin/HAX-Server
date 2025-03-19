package com.tencent.controller;

import com.tencent.config.ApiResponse;
import com.tencent.model.PracticeRecord;
import com.tencent.service.ChallengeService;
import com.tencent.service.PracticeRecordService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 挑战
 */
@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Resource
    public ChallengeService challengeService;


    /**
     * 功能：列表查询
     * 界面：挑战列表
     *
     * @param userId 用户ID
     * @return 挑战列表
     */
    @GetMapping("/getChallengeList")
    public ApiResponse getChallengeList(String userId) {
        return ApiResponse.ok(challengeService.getChallengeList(userId));
    }
}

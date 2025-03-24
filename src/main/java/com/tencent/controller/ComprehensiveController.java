package com.tencent.controller;


import com.tencent.config.ApiResponse;
import com.tencent.service.ComprehensiveService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 综合类：混合其他类的接口
 */
@RestController
@RequestMapping("/comprehensive")
public class ComprehensiveController {

    @Resource
    public ComprehensiveService comprehensiveService;

    /**
     * 功能：列表查询
     * 界面：挑战列表
     *
     * @param userId 用户ID
     * @return 挑战列表
     */
    @GetMapping("/getChallengeList")
    public ApiResponse getChallengeList(String userId) {
        return ApiResponse.ok(comprehensiveService.getChallengeList(userId));
    }

}

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
     * 功能：查询练习记录列表
     * 界面：练习记录列表
     * 功能完整度：2/2
     *
     * @param userId 用户ID
     * @return 练习详情
     */
    @GetMapping("/getPracticeRecordList")
    public ApiResponse getPracticeRecordList(String userId) {
        return ApiResponse.ok(comprehensiveService.getPracticeRecordList(userId));
    }

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

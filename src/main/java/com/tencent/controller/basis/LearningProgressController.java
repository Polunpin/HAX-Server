package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.model.LearningProgress;
import com.tencent.service.LearningProgressService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 学习进度
 */
@RestController()
@RequestMapping("/learningProgress")
public class LearningProgressController {

    @Resource
    public LearningProgressService learningProgressService;

    /**
     * 功能：学习进度
     * 界面：首页-学习进度
     *
     * @param userId 用户ID
     * @return 学习详情
     */
    @GetMapping("/getLearningProgress")
    public ApiResponse getLearningProgress(String userId) {
        return ApiResponse.ok(learningProgressService.getLearningProgress(userId));
    }

    /**
     * 功能：修改状态
     * 界面：知识点详情
     *
     * @return Boolean
     */
    @PostMapping("/saveOrUpdate")
    public ApiResponse saveOrUpdate(@RequestBody LearningProgress learningProgress) {
        //第一次学会时，补充第一次学会时间
        if (learningProgress.getLearningStatus() == 2 && learningProgress.getFirstMasteredAt() == null) {
            learningProgress.setFirstMasteredAt(new Date());
        }
        return ApiResponse.ok(learningProgressService.saveOrUpdate(learningProgress));
    }

}

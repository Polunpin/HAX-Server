package com.tencent.controller;

import com.tencent.config.ApiResponse;
import com.tencent.model.LearningProgress;
import com.tencent.service.KnowledgeLibraryService;
import com.tencent.service.LearningProgressService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 学习进度
 */
@Slf4j
@RestController()
@RequestMapping("/learningProgress")
public class LearningProgressController {

    @Resource
    public LearningProgressService learningProgressService;

    /**
     * 功能：修改状态
     * 界面：知识点详情
     * @return Boolean
     */
    @PostMapping("/update")
    public ApiResponse saveOrUpdate(@RequestBody LearningProgress learningProgress) {
        return ApiResponse.ok(learningProgressService.update(learningProgress));
    }

//    TODO 接口3：问助教（wecom）｜前端的活
}

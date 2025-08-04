package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.service.StudyKnowledgeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学习详情
 */
@RestController
@RequestMapping("/studyKnowledge")
public class StudyKnowledgeController {

    @Resource
    public StudyKnowledgeService studyKnowledgeService;

    /**
     * 功能：根据学习列表id获取详情
     * 界面：学习详情
     *
     * @param id 学习列表ID
     * @return List
     */
    @GetMapping("/listById")
    public ApiResponse listById(String id) {
        return ApiResponse.ok(studyKnowledgeService.listById(id));
    }

    /**
     * 功能：根据id获取详情
     * 界面：学习详情
     *
     * @param id 学习详情ID
     * @return List
     */
    @GetMapping("/getById")
    public ApiResponse getById(String id) {
        return ApiResponse.ok(studyKnowledgeService.getById(id));
    }
}

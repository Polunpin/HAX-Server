package com.tencent.controller.basis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.config.ApiResponse;
import com.tencent.model.StudyKnowledge;
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
     * 功能：根据id获取详情
     * 界面：学习详情
     *
     * @param id 学习目录ID
     * @return List
     */
    @GetMapping("/listById")
    public ApiResponse listById(String id) {
        QueryWrapper<StudyKnowledge> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("study_list_id", id);
        return ApiResponse.ok(studyKnowledgeService.list(queryWrapper));
    }
}

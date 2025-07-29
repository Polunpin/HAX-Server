package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.service.StudyListService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学习列表
 */
@RestController
@RequestMapping("/studyList")
public class StudyListController {

    @Resource
    public StudyListService studyListService;

    /**
     * 功能：根据id获取列表
     * 界面：学习列表
     *
     * @param id 学习目录ID
     * @return List
     */
    @GetMapping("/listById")
    public ApiResponse listById(String id) {
        return ApiResponse.ok(studyListService.listById(id));
    }
}

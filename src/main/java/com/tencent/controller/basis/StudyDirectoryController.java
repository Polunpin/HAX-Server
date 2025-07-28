package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.service.StudyDirectoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学习目录
 */
@RestController
@RequestMapping("/studyDirectory")
public class StudyDirectoryController {

    @Resource
    public StudyDirectoryService studyDirectoryService;

    /**
     * 功能：根据id获取列表
     * 界面：学习目录
     *
     * @return List
     */
    @GetMapping("/list")
    public ApiResponse list() {
        return ApiResponse.ok(studyDirectoryService.list());
    }
}

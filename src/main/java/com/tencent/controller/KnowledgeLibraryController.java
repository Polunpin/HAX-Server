package com.tencent.controller;

import com.tencent.config.ApiResponse;
import com.tencent.service.KnowledgeLibraryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 知识库
 */
@Slf4j
@RestController()
@RequestMapping("/knowledgeLibrary")
public class KnowledgeLibraryController {

    @Resource
    public KnowledgeLibraryService knowledgeLibrary;

    /**
     * 功能：根据一级分类查询知识点列表
     * 界面：学习列表
     * @return List<LearningProgressResponse>
     */
    @GetMapping("/listByLevel1Dir")
    public ApiResponse listByLevel1Dir(@RequestBody String level1Dir) {
        return ApiResponse.ok(knowledgeLibrary.listByLevel1Dir(level1Dir));
    }

    /**
     * 功能：根据二级目录查询三级级目录列表（包含四级所有目录）
     * 界面：知识详情页
     * @return List<KnowledgeLibrary>
     */
    @GetMapping("/listInfoByLevel2Dir")
    public ApiResponse listInfoByLevel2Dir(String secondLevel) {
        return ApiResponse.ok(knowledgeLibrary.listInfoByLevel2Dir(secondLevel));
    }

//    TODO 接口3：问助教（wecom）｜前端的活
}

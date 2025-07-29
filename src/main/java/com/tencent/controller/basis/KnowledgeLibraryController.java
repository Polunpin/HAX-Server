package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.request.KnowledgeLibraryRequest;
import com.tencent.service.KnowledgeLibraryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 知识库
 */
@Deprecated
@RestController()
@RequestMapping("/knowledgeLibrary")
public class KnowledgeLibraryController {

    @Resource
    public KnowledgeLibraryService knowledgeLibrary;

    /**
     * 功能：根据一级分类查询知识点列表
     * 界面：学习列表
     *
     * @return List<LearningProgressResponse>
     */
    @PostMapping("/listByLevel1Dir")
    public ApiResponse listByLevel1Dir(@RequestBody KnowledgeLibraryRequest knowledge) {
        return ApiResponse.ok(knowledgeLibrary.listByLevel1Dir(knowledge));
    }

    /**
     * 功能：根据二级目录查询三级级目录列表（包含四级所有目录）
     * 界面：知识详情页
     *
     * @return List<KnowledgeLibrary>
     */
    @PostMapping("/listInfoByLevel2Dir")
    public ApiResponse listInfoByLevel2Dir(@RequestBody KnowledgeLibraryRequest knowledge) {
        return ApiResponse.ok(knowledgeLibrary.listInfoByLevel2Dir(knowledge));
    }

}

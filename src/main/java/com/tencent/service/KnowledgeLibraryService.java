package com.tencent.service;

import com.tencent.model.KnowledgeLibrary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.request.KnowledgeLibraryRequest;
import com.tencent.response.LearningProgressResponse;

import java.util.List;

/**
* @author lanyiping
* @description 针对表【knowledge_library(知识库表)】的数据库操作Service
* @createDate 2025-03-19 22:06:35
*/
public interface KnowledgeLibraryService extends IService<KnowledgeLibrary> {

    List<LearningProgressResponse> listByLevel1Dir(KnowledgeLibraryRequest knowledge);

    List<KnowledgeLibrary> listInfoByLevel2Dir(String secondLevel);

}

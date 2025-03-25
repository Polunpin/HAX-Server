package com.tencent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.model.KnowledgeLibrary;
import com.tencent.request.KnowledgeLibraryRequest;
import com.tencent.response.KnowledgeLibraryResponse;
import com.tencent.response.LearningProgressResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【knowledge_library(知识库表)】的数据库操作Mapper
 * @createDate 2025-03-19 22:06:35
 * @Entity com.tencent.model.KnowledgeLibrary
 */
public interface KnowledgeLibraryMapper extends BaseMapper<KnowledgeLibrary> {

    List<LearningProgressResponse> listByLevel1Dir(KnowledgeLibraryRequest level1Dir);

    List<KnowledgeLibraryResponse> listInfoByLevel2Dir(KnowledgeLibraryRequest knowledge);
}





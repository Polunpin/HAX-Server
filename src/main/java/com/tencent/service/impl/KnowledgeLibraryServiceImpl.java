package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.model.KnowledgeLibrary;
import com.tencent.request.KnowledgeLibraryRequest;
import com.tencent.response.LearningProgressResponse;
import com.tencent.service.KnowledgeLibraryService;
import com.tencent.mapper.KnowledgeLibraryMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lanyiping
* @description 针对表【knowledge_library(知识库表)】的数据库操作Service实现
* @createDate 2025-03-19 22:06:35
*/
@Service
public class KnowledgeLibraryServiceImpl extends ServiceImpl<KnowledgeLibraryMapper, KnowledgeLibrary>
    implements KnowledgeLibraryService{

    @Resource
    public KnowledgeLibraryMapper knowledgeMapper;


    @Override
    public List<LearningProgressResponse> listByLevel1Dir(KnowledgeLibraryRequest knowledge) {
        //知识点列表
        return knowledgeMapper.listByLevel1Dir(knowledge);
    }

    @Override
    public List<KnowledgeLibrary> listInfoByLevel2Dir(KnowledgeLibraryRequest knowledge) {
        return knowledgeMapper.listInfoByLevel2Dir(knowledge);
    }
}





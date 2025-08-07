package com.tencent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.model.StudyKnowledge;
import com.tencent.response.StudyKnowledgeResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【study_knowledge】的数据库操作Service
 * @createDate 2025-07-29 01:51:35
 */
public interface StudyKnowledgeService extends IService<StudyKnowledge> {

    List<StudyKnowledgeResponse> listById(String id);

    StudyKnowledgeResponse getInfoById(String id);
}

package com.tencent.response;

import lombok.Data;


/**
 * 继续学习
 */
@Data
public class ContinueLearningResponse {

    /**
     * 知识ID，关联knowledge_library表
     */
    private Long knowledgeId;

    /**
     * 标题
     */
    private String title;
}

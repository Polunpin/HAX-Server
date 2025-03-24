package com.tencent.response;

import lombok.Data;


/**
 * 当前学习进度
 */
@Data
public class ContinueLearningResponse {

    /**
     * 知识ID，关联knowledge_library表
     */
    private Long knowledgeId;

    /**
     * 二级标题
     */
    private String level2Dir;

    /**
     * 标题
     */
    private String title;
}

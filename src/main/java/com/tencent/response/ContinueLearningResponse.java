package com.tencent.response;

import lombok.Data;


/**
 * 当前学习进度
 */
@Data
public class ContinueLearningResponse {

    /**
     * 知识ID,对应四级标题
     */
    private Long knowledgeId;

    /**
     * 二级标题
     */
    private String level2Dir;

    /**
     * 三级标题
     */
    private String level3Dir;

    /**
     * 四级标题
     */
    private String level4Dir;

    /**
     * 付费阶段
     */
    private String payStage;

    /**
     * 熟练度
     */
    private String proficiency;
}

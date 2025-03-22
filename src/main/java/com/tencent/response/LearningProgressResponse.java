package com.tencent.response;

import lombok.Data;


/**
 * 学习进度
 */
@Data
public class LearningProgressResponse {

    /**
     * 二级目录
     */
    private String level2Dir;

    /**
     * 知识点总数（二级目录下）
     */
    private Integer knowledgeSum;

    /**
     * 已学习总数（二级目录下）
     */
    private Integer learnCount;
}

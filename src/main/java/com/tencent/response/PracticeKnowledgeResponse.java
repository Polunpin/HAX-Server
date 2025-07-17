package com.tencent.response;

import lombok.Data;

/**
 * 练习知识点
 */
@Data
public class PracticeKnowledgeResponse {

    /**
     * 知识点
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否学习
     */
    private Integer state;

}
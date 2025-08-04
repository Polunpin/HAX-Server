package com.tencent.response;

import lombok.Data;

import java.util.List;

/**
 * 学习列表+知识点
 */
@Data
public class StudyKnowledgeListResponse {

    /**
     * 学习列表ID
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 缩略图URL
     */
    private String thumbnail;

    /**
     * 描述
     */
    private String description;

    /**
     * 知识点列表
     */
    private List<StudyKnowledgeResponse> knowledgeList;
}
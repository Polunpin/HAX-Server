package com.tencent.response;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * @TableName study_knowledge
 */
@Data
public class StudyKnowledgeResponse {
    /**
     * 学习详情ID
     */
    private Integer id;

    /**
     * 学习列表ID
     */
    private Integer studyListId;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 素材链接
     */
    private JSONArray urls;

    /**
     * 知识双链
     */
    private JSONObject blinks;

    /**
     * 练习ID
     */
    private String practiceId;
}
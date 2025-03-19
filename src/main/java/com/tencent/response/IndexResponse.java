package com.tencent.response;

import lombok.Data;

/**
 * 首页
 */
@Data
public class IndexResponse {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 熟练度
     */
    private Integer proficiency;

    /**
     * 当前学习进度
     */
    private String learningProgress;
}
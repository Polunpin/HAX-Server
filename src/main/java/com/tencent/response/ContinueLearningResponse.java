package com.tencent.response;

import lombok.Data;


/**
 * 当前学习进度
 */
@Data
public class ContinueLearningResponse {

    /**
     * 详情ID
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 学习目录ID
     */
    private String studyDirectoryId;
}

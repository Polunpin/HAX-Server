package com.tencent.response;

import lombok.Data;

/**
 * 用户-首页信息
 */
@Data
public class UserResponse {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 付费阶段：0-白户，1-知识，2-月付，3-买断
     */
    private Integer payStage;

    /**
     * 用户状态：1-正常，0-禁用
     */
    private Integer status;

    /**
     * 熟练度
     */
    private Integer proficiency;

    /**
     * 金币(练习/挑战所得)
     */
    private Integer gold;

    /**
     * 驾驶里程
     */
    private Integer mileage;

    /**
     * 当前学习进度
     */
    private ContinueLearningResponse continueLearning;
}
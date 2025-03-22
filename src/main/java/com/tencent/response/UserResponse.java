package com.tencent.response;

import com.tencent.model.LearningProgress;
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
     * 付费阶段：A-白户，B-知识，C-月付，D-买断
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
    private LearningProgress continueLearning;
}
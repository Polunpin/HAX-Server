package com.tencent.response;

import lombok.Data;

/**
 * 挑战列表
 */
@Data
public class ChallengesResponse {

    /**
     * 挑战ID
     */
    private Long id;

    /**
     * 挑战标题
     */
    private String title;

    /**
     * 挑战描述
     */
    private String description;

    /**
     * 当前进度
     */
    private String progress;

    /**
     * 挑战条件类型：1-次数, 2-里程, 3-综合
     */
    private Integer conditionType;

    /**
     * 状态：0-预备, 1-上架, 2-完成, 3-失效
     */
    private Integer status;

    /**
     * 条件值(次数/里程等)
     */
    private Integer conditionValue;

    /**
     * 奖励金币
     */
    private Integer rewardGold;

    /**
     * 排序字段
     */
    private Integer sortOrder;

}
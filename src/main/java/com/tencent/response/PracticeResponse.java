package com.tencent.response;

import lombok.Data;

import java.util.Date;

/**
 * 练习
 */
@Data
public class PracticeResponse {
    /**
     * 练习ID
     */
    private Long id;

    /**
     * 练习标题
     */
    private String title;

    /**
     * 练习目标
     */
    private Object target;

    /**
     * 注意事项
     */
    private String notes;

    /**
     * 练习类型：1-基础, 2-进阶, 3-突破
     */
    private Integer type;

    /**
     * 已练习时长
     */
    private String duration;

    /**
     * 奖励金币
     */
    private Integer rewardGold;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;
}
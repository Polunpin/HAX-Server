package com.tencent.response;

import lombok.Data;

import java.util.List;

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
     * 知识点ID
     */
    private String knowledgeId;

    /**
     * 练习类型
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
     * 知识点目录
     */
    private List<PracticeKnowledgeResponse> target;

}
package com.tencent.response;

import com.alibaba.fastjson2.JSONArray;
import lombok.Data;

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
    private JSONArray target;

    /**
     * 注意事项
     */
    private JSONArray notes;

    /**
     * 练习类型：1-基础, 2-进阶, 3-突破
     */
    private Integer type;

    /**
     * 图片链接
     */
    private String imageUrl;

    /**
     * 已练习时长
     */
    private String duration;

    /**
     * 奖励金币
     */
    private Integer rewardGold;

}
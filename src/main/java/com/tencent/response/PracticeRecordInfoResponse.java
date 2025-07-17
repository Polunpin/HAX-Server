package com.tencent.response;

import com.alibaba.fastjson2.JSONArray;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 练习记录
 */
@Data
public class PracticeRecordInfoResponse {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 练习类别
     */
    private String title;

    /**
     * 消耗时长(开始-结束)
     */
    private String consumptionTime;

    /**
     * 耗时
     */
    private String duration;

    /**
     * 路程(单位:公里)
     */
    private BigDecimal distance;

    /**
     * 平均速度(公里/小时)
     */
    private BigDecimal avgSpeed;

    /**
     * 最高速度(公里/小时)
     */
    private BigDecimal maxSpeed;

    /**
     * 急刹车次数
     */
    private Integer suddenBrakeCount;

    /**
     * 熟练度
     */
    private Integer proficiency;

    /**
     * 练习心得
     */
    private String insights;

    /**
     * 练习照片
     */
    private String photos;

    /**
     * 轨迹点(可存JSON)
     */
    private JSONArray trajectory;

}
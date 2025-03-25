package com.tencent.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 练习记录
 */
@Data
public class PracticeRecordResponse {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 练习类别
     */
    private String title;

    /**
     * 路程(单位:公里)
     */
    private BigDecimal distance;

    /**
     * 消耗时长(开始-结束)
     */
    private String consumptionTime;

    /**
     * 是否填写练习记录 0:未填写 1:已填写
     */
    private String isRecord;

    /**
     * 耗时
     */
    private String duration;

    /**
     * 轨迹点(可存JSON)
     */
    private String trajectory;

}
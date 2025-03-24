package com.tencent.response;

import lombok.Data;

import java.util.List;

/**
 * 练习记录列表
 */
@Data
public class PracticeRecordsResponse {

    /**
     * 练习类型
     */
    private String type;

    /**
     * 总里程
     */
    private double totalDistance;

    /**
     * 具体每条练习记录
     */
    private List<PracticeRecordResponse> practiceRecordResponses;

}
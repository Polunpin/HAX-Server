package com.tencent.request;

import lombok.Data;

/**
 * 练习详情
 */
@Data
public class PracticeRequest {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 练习ID
     */
    private Long practiceId;

}

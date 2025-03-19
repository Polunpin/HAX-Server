package com.tencent.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 练习记录表
 */
@Data
public class PracticeRecordsResponse {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 练习ID，关联practice表
     */
    private Long practiceId;

    /**
     * 练习标题
     */
    private String title;

    /**
     * 用户ID，关联users表
     */
    private Long userId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 耗时(单位:分钟)
     */
    private Date duration;

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
     * 练习表现
     */
    private Object performance;

    /**
     * 练习心得(200字内)
     */
    private String insights;

    /**
     * 练习照片(可存JSON或逗号分隔URL)
     */
    private String photos;

    /**
     * 轨迹点(可存JSON)
     */
    private Object trajectory;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
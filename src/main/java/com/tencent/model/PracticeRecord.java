package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 练习记录表
 *
 * @TableName user_practice_record
 */
@Data
@TableName(value = "user_practice_record")
public class PracticeRecord {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 练习ID，关联practice表
     */
    private Long practiceId;

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
     * 耗时(单位:小时)
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
     * 练习表现
     */
    private String performance;

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
    private String trajectory;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 挑战记录表
 *
 * @TableName challenge_record
 */
@Data
@TableName(value = "challenge_record")
public class ChallengeRecord {
    /**
     * 挑战记录ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 挑战ID,关联challenge表
     */
    private Long challengeId;

    /**
     * 用户ID,关联users表
     */
    private Long userId;

    /**
     * 结果(公域素材URL等)
     */
    private String result;

    /**
     * 挑战状态：0-进行中, 1-完成, 2-失败
     */
    private Integer status;

    /**
     * 领取时间
     */
    private Date collectAt;
}
package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户学习进度表
 *
 * @TableName user_learning_progress
 */
@Data
@TableName(value = "user_learning_progress")
public class LearningProgress {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID，关联users表
     */
    private Long userId;

    /**
     * 知识ID，关联knowledge_library表
     */
    private Long knowledgeId;

    /**
     * 学习状态：0-未开始,1-学习中,2-已掌握
     */
    private Integer learningStatus;

    /**
     * 累计学习次数
     */
    private Integer studyCount;

    /**
     * 首次掌握该知识点的时间
     */
    private Date firstMasteredAt;

    /**
     * 记录创建时间
     */
    private Date createdAt;

    /**
     * 记录更新时间
     */
    private Date updatedAt;
}
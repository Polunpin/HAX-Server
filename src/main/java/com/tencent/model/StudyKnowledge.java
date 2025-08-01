package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName study_knowledge
 */
@TableName(value = "study_knowledge")
@Data
public class StudyKnowledge {
    /**
     * 学习详情ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学习列表ID
     */
    private Integer studyListId;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 素材链接
     */
    private String urls;

    /**
     * 知识双链
     */
    private String blinks;

}
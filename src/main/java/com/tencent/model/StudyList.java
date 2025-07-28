package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 学习列表
 *
 * @TableName study_list
 */
@TableName(value = "study_list")
@Data
public class StudyList {
    /**
     * 学习列表ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学习目录ID
     */
    private Integer studyDirectoryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 缩略图URL
     */
    private String thumbnail;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;
}
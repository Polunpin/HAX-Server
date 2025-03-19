package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 知识库表
 * @TableName knowledge_library
 */
@TableName(value ="knowledge_library")
@Data
public class KnowledgeLibrary {
    /**
     * 知识ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 一级目录
     */
    private String level1Dir;

    /**
     * 二级目录
     */
    private String level2Dir;

    /**
     * 三级目录
     */
    private String level3Dir;

    /**
     * 四级目录
     */
    private String level4Dir;

    /**
     * 难度(1-5星)
     */
    private Integer difficulty;

    /**
     * 知识点图片URL
     */
    private String imageUrl;

    /**
     * 知识点的具体内容
     */
    private String content;

    /**
     * 排序字段
     */
    private Integer sortOrder;

    /**
     * 状态：1-上架, 0-下架
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;
}
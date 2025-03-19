package com.tencent.request;

import lombok.Data;

import java.util.Date;

/**
 * 一级目录查询
 */
@Data
public class FirstLevelRequest {
    /**
     * 知识ID
     */
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
package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 图片资源表
 *
 * @TableName image_resource
 */
@TableName(value = "image_resource")
@Data
public class ImageResource {
    /**
     * 资源ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 一级标题
     */
    private String level;

    /**
     * 用途描述
     */
    private String name;

    /**
     * 访问地址
     */
    private String url;

    /**
     * 第二个路径
     */
    private String urlSecond;

    /**
     * 类型(0:交通标志 1:学习图标)
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;
}
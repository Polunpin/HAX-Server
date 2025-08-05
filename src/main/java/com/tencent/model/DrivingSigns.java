package com.tencent.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 驾驶标志信息表
 *
 * @TableName driving_signs
 */
@TableName(value = "driving_signs")
@Data
public class DrivingSigns {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 一级标志
     */
    private String level1;

    /**
     * 二级标志
     */
    private String level2;

    /**
     * 二级标志
     */
    private String level3;

    /**
     * 属性详情
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片
     */
    private String image;

    /**
     * 文件上传ID
     */
    private String sourceFile;
}
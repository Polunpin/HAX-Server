package com.tencent.response;

import lombok.Data;

/**
 * 知识库表
 *
 * @TableName knowledge_library
 */
@Data
public class KnowledgeLibraryResponse {

    /**
     * 知识ID
     */
    private Long id;

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
     * 学习记录ID
     */
    private String learningId;

    /**
     * 学习状态：0-未开始,1-学习中,2-已掌握
     */
    private String learningStatus;

}
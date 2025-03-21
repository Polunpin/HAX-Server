package com.tencent.request;

import lombok.Data;

/**
 * 知识点列表请求对象
 */
@Data
public class KnowledgeLibraryRequest {

    /**
     * 一级分类
     */
    private String level1Dir;

    /**
     * 二级分类
     */
    private String level2Dir;

    /**
     * 用户ID
     */
    private String userId;

}

package com.tencent.response;

import com.tencent.model.StudyList;
import lombok.Data;

import java.util.List;


/**
 * 学习列表
 */
@Data
public class StudyListResponse {

    /**
     * 头图
     */
    private String headerImage;

    /**
     * 学习列表
     */
    private List<StudyList> studyList;
}

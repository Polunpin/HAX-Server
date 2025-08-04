package com.tencent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.StudyListMapper;
import com.tencent.model.StudyList;
import com.tencent.response.StudyKnowledgeListResponse;
import com.tencent.response.StudyKnowledgeResponse;
import com.tencent.response.StudyListResponse;
import com.tencent.service.StudyDirectoryService;
import com.tencent.service.StudyKnowledgeService;
import com.tencent.service.StudyListService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @author lanyiping
 * @description 针对表【study_list(学习列表)】的数据库操作Service实现
 * @createDate 2025-07-29 01:51:36
 */
@Service
public class StudyListServiceImpl extends ServiceImpl<StudyListMapper, StudyList>
        implements StudyListService {

    @Resource
    public StudyDirectoryService studyDirectoryService;
    @Resource
    public StudyKnowledgeService studyKnowledgeService;

    @Override
    public StudyListResponse listById(String id) {
        //列表头图
        String headerImage = studyDirectoryService.getById(id).getHeaderImage();
        //列表内容
        QueryWrapper<StudyList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("study_directory_id", id);

        StudyListResponse studyListResponse = new StudyListResponse();
        List<StudyList> list = this.list(queryWrapper);

        List<StudyKnowledgeListResponse> studyKnowledgeList = new ArrayList<>();

        list.forEach(studyList -> {
            StudyKnowledgeListResponse studyKnowledgeRep = new StudyKnowledgeListResponse();
            copyProperties(studyList, studyKnowledgeRep);
            List<StudyKnowledgeResponse> knowledgeList = studyKnowledgeService.listById(studyList.getId().toString());
            studyKnowledgeRep.setKnowledgeList(knowledgeList);
            studyKnowledgeList.add(studyKnowledgeRep);
        });

        studyListResponse.setStudyList(studyKnowledgeList);
        studyListResponse.setHeaderImage(headerImage);
        return studyListResponse;
    }
}





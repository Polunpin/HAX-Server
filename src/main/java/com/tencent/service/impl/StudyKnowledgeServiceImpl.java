package com.tencent.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.StudyKnowledgeMapper;
import com.tencent.model.StudyKnowledge;
import com.tencent.response.StudyKnowledgeResponse;
import com.tencent.service.StudyKnowledgeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @author lanyiping
 * @description 针对表【study_knowledge】的数据库操作Service实现
 * @createDate 2025-07-29 01:51:35
 */
@Service
public class StudyKnowledgeServiceImpl extends ServiceImpl<StudyKnowledgeMapper, StudyKnowledge>
        implements StudyKnowledgeService {

    @Override
    public List<StudyKnowledgeResponse> listById(String id) {
        QueryWrapper<StudyKnowledge> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("study_list_id", id);
        List<StudyKnowledge> list = this.list(queryWrapper);

        List<StudyKnowledgeResponse> arrayList = new ArrayList<>();
        for (StudyKnowledge studyKnowledge : list) {
            StudyKnowledgeResponse knowledgeRep = new StudyKnowledgeResponse();
            copyProperties(studyKnowledge, knowledgeRep);
            knowledgeRep.setUrls(JSON.parseArray(studyKnowledge.getUrls()));
            knowledgeRep.setBlinks(JSON.parseObject(studyKnowledge.getBlinks()));
            arrayList.add(knowledgeRep);
        }
        return arrayList;
    }
}





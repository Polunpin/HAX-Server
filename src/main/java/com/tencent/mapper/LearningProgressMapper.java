package com.tencent.mapper;

import com.tencent.model.LearningProgress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.response.ContinueLearningResponse;

/**
* @author lanyiping
* @description 针对表【learning_progress(用户学习进度表)】的数据库操作Mapper
* @createDate 2025-03-19 22:06:35
* @Entity com.tencent.model.LearningProgress
*/
public interface LearningProgressMapper extends BaseMapper<LearningProgress> {

    ContinueLearningResponse getLearningProgress(String userId);
}





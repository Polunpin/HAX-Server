package com.tencent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.model.LearningProgress;
import com.tencent.response.ContinueLearningResponse;

/**
* @author lanyiping
* @description 针对表【learning_progress(用户学习进度表)】的数据库操作Service
* @createDate 2025-03-19 22:06:35
*/
public interface LearningProgressService extends IService<LearningProgress> {

    ContinueLearningResponse getLearningProgress(String userId);
}

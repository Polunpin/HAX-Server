package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.LearningProgressMapper;
import com.tencent.model.LearningProgress;
import com.tencent.response.ContinueLearningResponse;
import com.tencent.service.LearningProgressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * @author lanyiping
 * @description 针对表【learning_progress(用户学习进度表)】的数据库操作Service实现
 * @createDate 2025-03-19 22:06:35
 */
@Service
public class LearningProgressServiceImpl extends ServiceImpl<LearningProgressMapper, LearningProgress>
        implements LearningProgressService {

    @Resource
    public LearningProgressMapper learningProgressMapper;

    @Override
    public ContinueLearningResponse getLearningProgress(String userId) {
        return learningProgressMapper.getLearningProgress(userId);
    }
}





package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.model.LearningProgress;
import com.tencent.service.LearningProgressService;
import com.tencent.mapper.LearningProgressMapper;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
* @author lanyiping
* @description 针对表【learning_progress(用户学习进度表)】的数据库操作Service实现
* @createDate 2025-03-19 22:06:35
*/
@Service
public class LearningProgressServiceImpl extends ServiceImpl<LearningProgressMapper, LearningProgress>
    implements LearningProgressService{

    @Override
    public Boolean update(LearningProgress learningProgress) {
        //第一次学会时，补充第一次学会时间
        if (learningProgress.getLearningStatus() == 2){
            learningProgress.setFirstMasteredAt(new Date());
        }
        return this.saveOrUpdate(learningProgress);
    }
}





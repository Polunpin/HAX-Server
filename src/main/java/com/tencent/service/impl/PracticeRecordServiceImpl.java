package com.tencent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.PracticeRecordMapper;
import com.tencent.model.PracticeRecord;
import com.tencent.model.Users;
import com.tencent.service.PracticeRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lanyiping
* @description 针对表【practice_record(练习记录表)】的数据库操作Service实现
* @createDate 2025-03-19 22:06:35
*/
@Service
public class PracticeRecordServiceImpl extends ServiceImpl<PracticeRecordMapper, PracticeRecord>
    implements PracticeRecordService{


    @Override
    public String durationSum(Long id) {
        QueryWrapper<PracticeRecord> queryWrapper = new QueryWrapper<>();
        //TODO 获取用户id
        queryWrapper.select("SUM(duration) as duration").eq("user_id","");
        return this.getOne(queryWrapper).getDuration().toString();
    }

    @Override
    public Long savePracticeRecord(PracticeRecord practiceRecord) {
        this.save(practiceRecord); // 保存实体
        return practiceRecord.getId(); // 返回自动生成的 ID
    }


    @Override
    public PracticeRecord getPracticeRecord(Long id) {
        return this.getById(id);
    }

    @Override
    public List<PracticeRecord> getPracticeRecordList(String userId) {
        QueryWrapper<PracticeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        return this.list(queryWrapper);
    }
}





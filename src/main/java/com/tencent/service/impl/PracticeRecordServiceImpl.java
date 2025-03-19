package com.tencent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.model.PracticeRecord;
import com.tencent.response.PracticeRecordsResponse;
import com.tencent.service.PracticeRecordService;
import com.tencent.mapper.PracticeRecordMapper;
import com.tencent.service.PracticeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
* @author lanyiping
* @description 针对表【practice_record(练习记录表)】的数据库操作Service实现
* @createDate 2025-03-19 22:06:35
*/
@Service
public class PracticeRecordServiceImpl extends ServiceImpl<PracticeRecordMapper, PracticeRecord>
    implements PracticeRecordService{

    @Resource
    public PracticeService practiceService;


    @Override
    public String durationSum(Long id) {
        QueryWrapper<PracticeRecord> queryWrapper = Wrappers.query();
        queryWrapper.select("SUM(duration) as duration");
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
    public List<PracticeRecordsResponse> getPracticeRecordList(String userId) {
        List<PracticeRecordsResponse> practiceRecords = new ArrayList<>();
        QueryWrapper<PracticeRecord> queryWrapper = Wrappers.query();
        queryWrapper.eq("userId",userId);
        List<PracticeRecord> list = this.list(queryWrapper);
        copyProperties(list, practiceRecords);
        for (PracticeRecordsResponse practiceRecord : practiceRecords) {
            practiceRecord.setTitle(practiceService.getById(practiceRecord.getPracticeId()).getTitle());
        }
        return practiceRecords;
    }
}





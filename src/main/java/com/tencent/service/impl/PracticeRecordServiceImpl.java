package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.PracticeRecordMapper;
import com.tencent.model.PracticeRecord;
import com.tencent.response.PracticeRecordResponse;
import com.tencent.response.PracticeRecordsResponse;
import com.tencent.service.PracticeRecordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lanyiping
 * @description 针对表【practice_record(练习记录表)】的数据库操作Service实现
 * @createDate 2025-03-19 22:06:35
 */
@Service
public class PracticeRecordServiceImpl extends ServiceImpl<PracticeRecordMapper, PracticeRecord>
        implements PracticeRecordService {


    @Resource
    public PracticeRecordMapper practiceRecordMapper;

    @Override
    public Long savePracticeRecord(PracticeRecord practiceRecord) {
        this.save(practiceRecord); // 保存实体
        return practiceRecord.getId(); // 返回自动生成的 ID
    }

    @Override
    public List<PracticeRecordsResponse> getPracticeRecordList(String userId) {
        //根据userId获取全部练习记录
        List<PracticeRecordResponse> practiceRecordList = practiceRecordMapper.getPracticeRecordList(userId);
        //根据title分类，计算distance(驾驶路程)总和
        return practiceRecordList.stream()
                .collect(Collectors.groupingBy(PracticeRecordResponse::getTitle))
                .entrySet()
                .stream()
                .map(entry -> {
                    PracticeRecordsResponse response = new PracticeRecordsResponse();
                    response.setType(entry.getKey());
                    response.setTotalDistance(entry.getValue().stream()
                            .map(PracticeRecordResponse::getDistance)
                            .filter(Objects::nonNull)
                            .mapToDouble(BigDecimal::doubleValue)
                            .sum());
                    response.setPracticeRecord(entry.getValue());
                    return response;
                }).toList();
    }
}





package com.tencent.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.PracticeRecordMapper;
import com.tencent.model.PracticeRecord;
import com.tencent.response.PracticeRecordResponse;
import com.tencent.response.PracticeRecordsResponse;
import com.tencent.service.PracticeRecordService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lanyiping
 * @description 针对表【user_practice_record(练习记录表)】的数据库操作Service实现
 * @createDate 2025-03-19 22:06:35
 */
@Slf4j
@Service
public class PracticeRecordServiceImpl extends ServiceImpl<PracticeRecordMapper, PracticeRecord>
        implements PracticeRecordService {


    @Resource
    public PracticeRecordMapper practiceRecordMapper;

    @Override
    public Long savePracticeRecord(PracticeRecord practiceRecord) {

        if (practiceRecord.getTrajectory() != null) {
            JSONArray speeds = JSON.parseArray(practiceRecord.getTrajectory());
            //最快速度
            double maxSpeed = 0;
            //急刹车次数
            int suddenBrakeCount = 0;
            //轨迹点的速度总和
            double totalSpeed = 0;
            //记录上一个轨迹点的速度，用于计算急刹车
            double prevSpeed = -1;

            for (int i = 0; i < speeds.size(); i++) {
                double currentSpeed = speeds.getJSONObject(i).getDoubleValue("speed");
                totalSpeed += currentSpeed;

                if (currentSpeed > maxSpeed) {
                    maxSpeed = currentSpeed;
                }

                if (prevSpeed >= 0 && prevSpeed - currentSpeed > 5) { // Threshold for sudden brake
                    suddenBrakeCount++;
                }
                prevSpeed = currentSpeed;
            }
            //平均速度
            double averageSpeed = totalSpeed / speeds.size();
            practiceRecord.setAvgSpeed(BigDecimal.valueOf(averageSpeed * 3.6)); // m/s to km/h
            practiceRecord.setMaxSpeed(BigDecimal.valueOf(maxSpeed * 3.6)); // m/s to km/h
            practiceRecord.setSuddenBrakeCount(suddenBrakeCount);
        }
        this.saveOrUpdate(practiceRecord);
        // 返回自动生成的 ID
        return practiceRecord.getId();
    }

    @Override
    public List<PracticeRecordsResponse> getPracticeRecordList(String userId) {
        // 根据userId获取全部练习记录
        List<PracticeRecordResponse> practiceRecordList = practiceRecordMapper.getPracticeRecordList(userId);

        // 遍历练习记录并将trajectory字段转为JSON
        practiceRecordList.forEach(record -> {
            if (record.getTrajectory() != null) {
                record.setTrajectory(JSON.parseArray(String.valueOf(record.getTrajectory())));
            }
        });

        // 根据title分类，计算distance(驾驶路程)总和
        List<PracticeRecordsResponse> list = practiceRecordList.stream()
                .collect(Collectors.groupingBy(PracticeRecordResponse::getTitle))
                .entrySet()
                .stream()
                .map(entry -> {
                    PracticeRecordsResponse response = new PracticeRecordsResponse();
                    response.setType(entry.getKey());
                    response.setTotalDistance(
                            BigDecimal.valueOf(entry.getValue().stream()
                                            .map(PracticeRecordResponse::getDistance)
                                            .filter(Objects::nonNull)
                                            .mapToDouble(BigDecimal::doubleValue)
                                            .sum())
                                    .setScale(2, RoundingMode.HALF_UP)
                                    .doubleValue());
                    response.setPracticeRecord(entry.getValue());
                    return response;
                }).toList();

        // 找到list中最新的记录并设置标志
        if (!list.isEmpty()) {
            // 假设最新记录是根据练习记录的结束时间来判断
            practiceRecordList.stream()
                    .filter(record -> record.getEndTime() != null)
                    .max(Comparator.comparing(PracticeRecordResponse::getEndTime)).ifPresent(
                            latestRecordOverall -> list.forEach(response -> {
                                response.setLatestRecord(false); // 默认设为false
                                List<PracticeRecordResponse> records = response.getPracticeRecord();
                                if (records != null && !records.isEmpty()) {
                                    // 如果当前组包含最新的记录，则标记该组为最新
                                    if (records.contains(latestRecordOverall)) {
                                        response.setLatestRecord(true);
                                    }
                                }
                            }));
        }

        // 将带有最新记录的项移到列表最前面
        return list.stream()
                .sorted((r1, r2) -> {
                    boolean r1HasLatest = Boolean.TRUE.equals(r1.getLatestRecord());
                    boolean r2HasLatest = Boolean.TRUE.equals(r2.getLatestRecord());
                    return Boolean.compare(r2HasLatest, r1HasLatest); // true值在前
                })
                .collect(Collectors.toList());
    }
}





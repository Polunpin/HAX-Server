package com.tencent.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.PracticeRecordMapper;
import com.tencent.model.Practice;
import com.tencent.model.PracticeRecord;
import com.tencent.request.PracticeRequest;
import com.tencent.response.PracticeRecordResponse;
import com.tencent.response.PracticeRecordsResponse;
import com.tencent.service.PracticeRecordService;
import com.tencent.service.PracticeService;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
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
    public PracticeService practiceS;
    @Resource
    public PracticeRecordMapper practiceRecordMapper;

    @Override
    public Long savePracticeRecord(PracticeRecord practiceRecord) {
        //保存练习记录时，同步练习表现
        if (practiceRecord.getPracticeId() != null) {
            Practice practice = practiceS.getById(practiceRecord.getPracticeId());
            if (practice != null && practice.getTarget() != null) {
                practiceRecord.setPerformance(practice.getTarget());
            }
        }

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
        if (practiceRecord.getDuration() != null) {
            // 将秒数直接转换为 HH:mm:ss 并保存
            Duration duration = Duration.ofSeconds(Long.parseLong(practiceRecord.getDuration()));
            practiceRecord.setDuration(
                    String.format("%02d:%02d:%02d",
                            duration.toHoursPart(),
                            duration.toMinutesPart(),
                            duration.toSecondsPart()));
        }
        this.saveOrUpdate(practiceRecord); // 保存实体
        return practiceRecord.getId(); // 返回自动生成的 ID
    }

    @Override
    public List<PracticeRecordsResponse> getPracticeRecordList(String userId) {
        //根据userId获取全部练习记录
        List<PracticeRecordResponse> practiceRecordList = practiceRecordMapper.getPracticeRecordList(userId);
        // 遍历练习记录并将trajectory字段转为JSON
        practiceRecordList.forEach(record -> {
            if (record.getTrajectory() != null) {
                record.setTrajectory(JSON.parseArray(String.valueOf(record.getTrajectory())));
            }
        });
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

    @Override
    public List<ContentStatistics> getPracticeProgress(PracticeRequest practiceRequest) {
        QueryWrapper<PracticeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", practiceRequest.getUserId());
        queryWrapper.eq("practice_id", practiceRequest.getPracticeId());
        //查询当前用户的练习记录（根据practice_id、user_id筛选）
        List<PracticeRecord> list = this.list(queryWrapper);
        //汇集所有练习表现
        List<MyPractice> practiceListAll = list.stream()
                .flatMap(record -> JSON.parseArray(record.getPerformance(), MyPractice.class).stream())
                .toList();
        //rate取值范围1-5
        int maxRate = 5;
        //对content分组，并计算SUN(rate),再计算rate百分比
        return practiceListAll.stream()
                .collect(Collectors.groupingBy(MyPractice::getTitle))
                .entrySet()
                .stream()
                .map(entry -> {
                    String content = entry.getKey();
                    List<MyPractice> practices = entry.getValue();
                    int count = practices.size();
                    int rateSum = practices.stream().mapToInt(MyPractice::getRate).sum();
                    double percentage = (rateSum * 100.0) / (count * maxRate);
                    ContentStatistics contentStatistics = new ContentStatistics();
                    contentStatistics.setContent(content);
                    contentStatistics.setCompletion(percentage);
                    return contentStatistics;
                }).toList();
    }

    // 定义用于映射的 POJO 类
    @Data
    public static class MyPractice {
        private String title;
        private Integer rate;
    }

    @Data
    public static class ContentStatistics {
        private String content; // content 名称
        private Double completion;    // rate 的百分比

    }
}





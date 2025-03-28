package com.tencent.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.PracticeRecordMapper;
import com.tencent.model.Practice;
import com.tencent.model.PracticeRecord;
import com.tencent.response.PracticeRecordResponse;
import com.tencent.response.PracticeRecordsResponse;
import com.tencent.service.PracticeRecordService;
import com.tencent.service.PracticeService;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
            JSONArray jsonArray = JSONArray.parseArray(practice.getTarget());

            // 遍历数组并修改属性名
            for (int i = 0; i < jsonArray.size(); i++) {
                // 获取对象
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // 获取completion的值并删除此属性
                Object contentValue = jsonObject.remove("content");
                // 获取completion的值并删除此属性
                Object completionValue = jsonObject.remove("completion");

                // 放入一个新属性title
                jsonObject.put("title", contentValue);
                // 放入一个新属性rate
                jsonObject.put("rate", completionValue);
            }
            practice.setTarget(jsonArray.toJSONString());
            practiceRecord.setPerformance(practice.getTarget());
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
    public Object getPracticeProgress(String userId) {
        QueryWrapper<PracticeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<PracticeRecord> list = this.list(queryWrapper);

        List<MyPractice> practiceListAll = new ArrayList<>();

        list.forEach(record -> {
            List<MyPractice> practiceList = JSON.parseArray(record.getPerformance(), MyPractice.class);
            practiceListAll.addAll(practiceList);
        });

        // Step 3: 按 content 分组，统计出现次数和 rate 的总和
        List<ContentStatistics> result = practiceListAll.stream()
                .collect(Collectors.groupingBy(
                        MyPractice::getContent, // 按 content 分组
                        Collectors.toList()
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    String content = entry.getKey(); // 当前 content 名称
                    List<MyPractice> practices = entry.getValue();

                    // 统计出现次数和 rate 总和
                    long count = practices.size(); // 出现次数
                    int rateSum = practices.stream().mapToInt(MyPractice::getRate).sum(); // rate 总和

                    return new ContentStatistics(content, count, rateSum); // 构建结果对象
                }).toList();
        return result;
    }

    // 定义用于映射的 POJO 类
    @Data
    public static class MyPractice {
        private String content;
        private Integer rate;
    }

    @Data
    public static class ContentStatistics {
        private String content; // content 名称
        private long count;     // content 出现的次数
        private int rateSum;    // rate 的总和

        public ContentStatistics(String content, long count, int rateSum) {
            this.content = content;
            this.count = count;
            this.rateSum = rateSum;
        }
    }
}





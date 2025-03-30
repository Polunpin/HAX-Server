package com.tencent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.model.PracticeRecord;
import com.tencent.request.PracticeRequest;
import com.tencent.response.PracticeRecordsResponse;
import com.tencent.service.impl.PracticeRecordServiceImpl;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【user_practice_record(练习记录表)】的数据库操作Service
 * @createDate 2025-03-19 22:06:35
 */
public interface PracticeRecordService extends IService<PracticeRecord> {

    Long savePracticeRecord(PracticeRecord practiceRecord);

    List<PracticeRecordsResponse> getPracticeRecordList(String userId);

    List<PracticeRecordServiceImpl.ContentStatistics> getPracticeProgress(PracticeRequest practiceRequest);
}

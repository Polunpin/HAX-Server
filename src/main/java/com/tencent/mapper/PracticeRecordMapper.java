package com.tencent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.model.PracticeRecord;
import com.tencent.response.PracticeRecordResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【practice_record(练习记录表)】的数据库操作Mapper
 * @createDate 2025-03-19 22:06:35
 * @Entity com.tencent.model.PracticeRecord
 */
public interface PracticeRecordMapper extends BaseMapper<PracticeRecord> {

    List<PracticeRecordResponse> getPracticeRecordList(String userId);
}





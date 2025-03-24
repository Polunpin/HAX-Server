package com.tencent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.model.Practice;
import com.tencent.response.PracticeResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【practice(练习表)】的数据库操作Mapper
 * @createDate 2025-03-19 22:06:35
 * @Entity com.tencent.model.Practice
 */
public interface PracticeMapper extends BaseMapper<Practice> {

    List<PracticeResponse> getPracticeList(String userId);
}





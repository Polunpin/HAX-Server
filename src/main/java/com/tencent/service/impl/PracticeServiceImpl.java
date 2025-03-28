package com.tencent.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.PracticeMapper;
import com.tencent.model.Practice;
import com.tencent.response.PracticeResponse;
import com.tencent.service.PracticeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【practice(练习表)】的数据库操作Service实现
 * @createDate 2025-03-19 22:06:35
 */
@Service
public class PracticeServiceImpl extends ServiceImpl<PracticeMapper, Practice>
        implements PracticeService {

    @Resource
    public PracticeMapper practiceMapper;

    @Override
    public List<PracticeResponse> getPracticeList(String userId) {
        List<PracticeResponse> practiceList = practiceMapper.getPracticeList(userId);
        practiceList.forEach(practice -> {
            practice.setTarget(JSON.parseArray(String.valueOf(practice.getTarget())));
            practice.setNotes(JSON.parseArray(String.valueOf(practice.getNotes())));
        });
        return practiceList;
    }


}





package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.PracticeMapper;
import com.tencent.model.Practice;
import com.tencent.service.PracticeRecordService;
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
    implements PracticeService{

    @Resource
    public PracticeRecordService practiceRecordService;


    @Override
    public List<Practice> getPracticeList() {
        List<Practice> list = this.list();
        list.forEach(item -> {
            //获取当前练习总耗时
            item.setDuration(practiceRecordService.durationSum(item.getId()));
        });
        return list;
    }


    @Override
    public Practice getPracticeDetail(Long id) {
        return this.getById(id);
    }

}





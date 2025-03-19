package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.model.Practice;
import com.tencent.service.PracticeService;
import com.tencent.mapper.PracticeMapper;
import org.springframework.stereotype.Service;

/**
* @author lanyiping
* @description 针对表【practice(练习表)】的数据库操作Service实现
* @createDate 2025-03-19 22:06:35
*/
@Service
public class PracticeServiceImpl extends ServiceImpl<PracticeMapper, Practice>
    implements PracticeService{

}





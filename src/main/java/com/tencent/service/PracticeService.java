package com.tencent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.model.Practice;
import com.tencent.response.PracticeResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【practice(练习表)】的数据库操作Service
 * @createDate 2025-03-19 22:06:35
 */
public interface PracticeService extends IService<Practice> {

    List<PracticeResponse> getPracticeList(String userId);

    Practice getPracticeDetail(String id);

}

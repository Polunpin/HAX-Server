package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.model.LearningProgress;
import com.tencent.service.LearningProgressService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 学习进度
 */
@Slf4j
@RestController()
@RequestMapping("/learningProgress")
public class LearningProgressController {

    @Resource
    public LearningProgressService learningProgressService;

    /**
     * 功能：修改状态
     * 界面：知识点详情
     *
     * @return Boolean
     */
    @PostMapping("/saveOrUpdate")
    public ApiResponse saveOrUpdate(@RequestBody LearningProgress learningProgress) {
        //第一次学会时，补充第一次学会时间
        if (learningProgress.getLearningStatus() == 2 && learningProgress.getFirstMasteredAt() == null){
            learningProgress.setFirstMasteredAt(new Date());
        }
        return ApiResponse.ok(learningProgressService.saveOrUpdate(learningProgress));
    }

//    TODO 接口3：问助教（wecom）｜前端的活
}

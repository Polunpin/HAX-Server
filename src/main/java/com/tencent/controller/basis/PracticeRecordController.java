package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.model.PracticeRecord;
import com.tencent.service.PracticeRecordService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 练习记录
 */
@RestController
@RequestMapping("/practiceRecord")
public class PracticeRecordController {

    @Resource
    public PracticeRecordService practiceRecordService;

    /**
     * 功能：保存练习记录
     * 界面：练习总结
     *
     * @param practiceRecord 练习表现、练习心得、练习照片
     * @return Boolean
     */
    @PostMapping("/savePracticeRecord")
    public ApiResponse savePracticeRecord(@RequestBody PracticeRecord practiceRecord) {
        return ApiResponse.ok(practiceRecordService.savePracticeRecord(practiceRecord));
    }

    /**
     * 功能：查询练习记录详情
     * 界面：练习报告、练习记录列表详情页
     *
     * @param id 练习记录ID
     * @return 练习详情
     */
    @GetMapping("getPracticeRecord")
    public ApiResponse getPracticeRecord(Long id) {
        return ApiResponse.ok(practiceRecordService.getPracticeRecord(id));
    }

    /**
     * 功能：查询练习记录列表
     * 界面：练习记录列表
     * 功能完整度：1/2
     *
     * @param userId 用户ID
     * @return 练习详情
     */
    @GetMapping("/getPracticeRecordList")
    public ApiResponse getPracticeRecordList(String userId) {
        return ApiResponse.ok(practiceRecordService.getPracticeRecordList(userId));
    }
}

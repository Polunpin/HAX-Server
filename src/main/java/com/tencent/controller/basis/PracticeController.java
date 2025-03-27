package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.service.PracticeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 练习
 */
@RestController
@RequestMapping("/practice")
public class PracticeController {

    @Resource
    public PracticeService practiceService;


    /**
     * 功能：练习列表查询
     * 界面：练习列表
     * 功能完整度：2/2
     *
     * @param userId 用户ID
     * @return 所有练习的列表
     */
    @GetMapping("/getPracticeList")
    public ApiResponse getPracticeList(String userId) {
        return ApiResponse.ok(practiceService.getPracticeList(userId));
    }

    /**
     * 功能：练习详情查询
     * 界面：练习详情页
     *
     * @param id 练习ID
     * @return 练习详情信息
     */
    @GetMapping("/getPracticeDetail")
    public ApiResponse getPracticeDetail(String id) {
        // 根据ID查询练习详情，如 练习目标、注意事项、类型、已练习时长 等
        return ApiResponse.ok(practiceService.getPracticeDetail(id));
    }

}

package com.tencent.controller.basis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.config.ApiResponse;
import com.tencent.model.DrivingSigns;
import com.tencent.service.DrivingSignsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 驾驶标志
 */
@RestController
@RequestMapping("/drivingSigns")
public class DrivingSignsController {

    @Resource
    public DrivingSignsService drivingSignsService;

    /**
     * 功能：根据level2获取资源列表
     * 界面：交通标志列表
     *
     * @param level2 交通 二级标志
     * @return List
     */
    @GetMapping("/listByLevel2Dir")
    public ApiResponse listByLevel2Dir(String level2) {
        // 根据type查询对应图片资源(type编号看SQL表结构)
        QueryWrapper<DrivingSigns> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level2", level2);
        return ApiResponse.ok(drivingSignsService.list(queryWrapper));
    }
}

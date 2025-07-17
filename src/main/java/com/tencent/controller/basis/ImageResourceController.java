package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.service.ImageResourceService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图片资源
 */
@RestController
@RequestMapping("/imageResources")
public class ImageResourceController {

    @Resource
    public ImageResourceService imageResourceService;

    /**
     * 功能：根据ID获取资源访问路径
     * 界面：X
     *
     * @param id 资源ID
     * @return String 图片URL
     */
    @RequestMapping("/getResourceUrlById")
    public ApiResponse getResourceUrlById(Long id) {
        return ApiResponse.ok(imageResourceService.getResourceUrlById(id));
    }

    /**
     * 功能：根据type获取资源列表
     * 界面：交通标志、学习一级目录
     *
     * @param type 资源类型
     * @return List
     */
    @RequestMapping("/getResourcesByType")
    public ApiResponse getResourcesByType(Integer type) {
        return ApiResponse.ok(imageResourceService.getResourcesByType(type));
    }

}
package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.service.UsersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    public UsersService usersService;

    /**
     * 功能：用户详情
     * 界面：X
     *
     * @param unionId 微信全平台统一ID
     * @return Users
     */
    @GetMapping("/info")
    public ApiResponse info(String unionId) {
        return ApiResponse.ok(usersService.getUserInfo(unionId));
    }

}

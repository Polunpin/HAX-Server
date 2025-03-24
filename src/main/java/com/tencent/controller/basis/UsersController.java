package com.tencent.controller.basis;

import com.tencent.config.ApiResponse;
import com.tencent.service.UsersService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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
     * @param request 请求体
     * @return Users
     */
    @GetMapping("/info")
    public ApiResponse info(HttpServletRequest request) {
        // 获取指定header参数的unionId
        String unionId = request.getHeader("X-WX-UNIONID");
        if (unionId == null) {
            return ApiResponse.error("unionId为空");
        }
        return ApiResponse.ok(usersService.getUserInfo(unionId));
    }

}

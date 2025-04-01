package com.tencent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.model.Users;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lanyiping
 * @description 针对表【users(用户表)】的数据库操作Service
 * @createDate 2025-03-19 22:42:50
 */
public interface UsersService extends IService<Users> {

    Users getUserInfo(HttpServletRequest request);
}

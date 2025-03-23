package com.tencent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.model.Users;
import com.tencent.service.UsersService;
import com.tencent.mapper.UsersMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Enumeration;

/**
* @author lanyiping
* @description 针对表【users(用户表)】的数据库操作Service实现
* @createDate 2025-03-19 22:42:50
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

    @Override
    public Users getUserInfo(HttpServletRequest request) {
        // 获取指定header参数的unionId
        String unionId = request.getHeader("X-WX-UNIONID");
        // 获取全部header参数的名称和值
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ": " + headerValue);
        }
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("union_id",unionId);
        return this.getOne(queryWrapper);
    }
}





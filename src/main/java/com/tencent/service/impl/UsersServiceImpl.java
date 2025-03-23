package com.tencent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.UsersMapper;
import com.tencent.model.Users;
import com.tencent.service.UsersService;
import org.springframework.stereotype.Service;

/**
* @author lanyiping
* @description 针对表【users(用户表)】的数据库操作Service实现
* @createDate 2025-03-19 22:42:50
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

    @Override
    public Users getUserInfo(String unionId) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("union_id",unionId);
        Users user = this.getOne(queryWrapper);
        if(user != null){
            return user;
        } else {
            Users users = new Users();
            users.setUnionId(unionId);
            this.save(users);
            return this.getById(users.getId());
        }
    }
}


package com.tencent.controller.basis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.config.ApiResponse;
import com.tencent.model.Users;
import com.tencent.response.IndexResponse;
import com.tencent.service.UsersService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * index控制器
 */
@Slf4j
@RestController()
@RequestMapping("/index")
public class IndexController {

    @Resource
    public UsersService usersService;


    /**
     * 主页页面
     *
     * @return API response html
     */
    @GetMapping
    public String index() {
        return "index";
    }

    /**
     * 功能：首页查询
     * 界面：小程序首页
     *
     * @return IndexResponse
     */
    @GetMapping("/info")
    public ApiResponse info(String unionId) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("union_id", unionId);
        Users one = usersService.getOne(queryWrapper);
        if (one == null) {
            return ApiResponse.error("用户不存在");
        }
        var indexResponse = new IndexResponse();
        indexResponse.setProficiency(one.getProficiency());
        //TODO 当前学习进度
        return ApiResponse.ok(indexResponse);
    }
}

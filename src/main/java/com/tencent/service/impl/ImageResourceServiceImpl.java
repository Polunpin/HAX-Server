package com.tencent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.ImageResourceMapper;
import com.tencent.model.ImageResource;
import com.tencent.service.ImageResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【image_resource(图片资源表)】的数据库操作Service实现
 * @createDate 2025-07-16 22:34:04
 */
@Service
public class ImageResourceServiceImpl extends ServiceImpl<ImageResourceMapper, ImageResource>
        implements ImageResourceService {

    @Override
    public String getResourceUrlById(Long id) {
        return this.getById(id).getUrl();
    }

    @Override
    public List<ImageResource> getResourcesByType(Integer type) {
        // 根据type查询对应图片资源(type编号看SQL表结构)
        QueryWrapper<ImageResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        return this.list(queryWrapper);
    }
}





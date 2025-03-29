package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.ImageResourceMapper;
import com.tencent.model.ImageResource;
import com.tencent.service.ImageResourceService;
import org.springframework.stereotype.Service;

/**
 * @author lanyiping
 * @description 针对表【image_resource(图片资源表)】的数据库操作Service实现
 * @createDate 2025-03-29 20:11:01
 */
@Service
public class ImageResourceServiceImpl extends ServiceImpl<ImageResourceMapper, ImageResource>
        implements ImageResourceService {

    @Override
    public String getResourceUrlById(Long id) {
        return this.getById(id).getUrl();
    }
}





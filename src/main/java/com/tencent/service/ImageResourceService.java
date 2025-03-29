package com.tencent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.model.ImageResource;

/**
 * @author lanyiping
 * @description 针对表【image_resource(图片资源表)】的数据库操作Service
 * @createDate 2025-03-29 20:11:01
 */
public interface ImageResourceService extends IService<ImageResource> {

    String getResourceUrlById(Long id);
}

package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.ShippingAddressMapper;
import com.tencent.model.ShippingAddress;
import com.tencent.service.ShippingAddressService;
import org.springframework.stereotype.Service;

/**
 * @author lanyiping
 * @description 针对表【shipping_address(用户收货地址表)】的数据库操作Service实现
 * @createDate 2025-07-17 12:09:44
 */
@Service
public class ShippingAddressServiceImpl extends ServiceImpl<ShippingAddressMapper, ShippingAddress>
        implements ShippingAddressService {

}





package com.tencent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.model.Redemption;
import com.tencent.response.RedemptionListResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【redemption(兑换表)】的数据库操作Mapper
 * @createDate 2025-03-21 17:27:14
 * @Entity com.tencent.model.Redemption
 */
public interface RedemptionMapper extends BaseMapper<Redemption> {

    List<RedemptionListResponse> getRewardExchangeList(String userId);
}





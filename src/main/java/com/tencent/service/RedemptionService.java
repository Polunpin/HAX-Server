package com.tencent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.model.Redemption;
import com.tencent.request.RedemptionRequest;
import com.tencent.response.RedemptionListResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【redemption(兑换表)】的数据库操作Service
 * @createDate 2025-03-21 17:27:14
 */
public interface RedemptionService extends IService<Redemption> {

    boolean exchange(RedemptionRequest redemptionRequest);

    List<RedemptionListResponse> getRewardExchangeList(String userId);
}

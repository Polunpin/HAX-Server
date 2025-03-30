package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.RedemptionMapper;
import com.tencent.model.Redemption;
import com.tencent.request.RedemptionRequest;
import com.tencent.response.RedemptionListResponse;
import com.tencent.service.RedemptionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lanyiping
 * @description 针对表【user_redemption(兑换表)】的数据库操作Service实现
 * @createDate 2025-03-21 17:27:14
 */
@Service
public class RedemptionServiceImpl extends ServiceImpl<RedemptionMapper, Redemption>
        implements RedemptionService {

    @Resource
    public RedemptionMapper RedemptionMapper;

    @Override
    public List<RedemptionListResponse> getRewardExchangeList(String userId) {
        return RedemptionMapper.getRewardExchangeList(userId);
    }

    @Override
    public boolean exchange(RedemptionRequest RedemptionRequest) {
        var user_redemption = new Redemption();
        user_redemption.setUserId(RedemptionRequest.getUserId());
        user_redemption.setRewardId(RedemptionRequest.getRewardId());
        return this.save(user_redemption);
    }

}





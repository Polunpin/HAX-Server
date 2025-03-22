package com.tencent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.model.Redemption;
import com.tencent.request.RedemptionRequest;
import com.tencent.service.RedemptionService;
import com.tencent.mapper.RedemptionMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author lanyiping
* @description 针对表【redemption(兑换表)】的数据库操作Service实现
* @createDate 2025-03-21 17:27:14
*/
@Service
public class RedemptionServiceImpl extends ServiceImpl<RedemptionMapper, Redemption>
    implements RedemptionService{

    @Override
    public List<Redemption> getRewardExchangeList(String userId) {
        QueryWrapper<Redemption> query = new QueryWrapper<>();
        query.eq("user_id",userId);
        return this.list(query);
    }

    @Override
    public boolean exchange(RedemptionRequest redemptionRequest) {
        var redemption = new Redemption();
        redemption.setUserId(redemptionRequest.getUserId());
        redemption.setRewardId(redemptionRequest.getRewardId());
        redemption.setExchangeTime(new Date());
        return this.save(redemption);
    }
}





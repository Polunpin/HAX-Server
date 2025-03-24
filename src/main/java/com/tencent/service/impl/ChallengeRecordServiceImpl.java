package com.tencent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mapper.ChallengeRecordMapper;
import com.tencent.model.ChallengeRecord;
import com.tencent.service.ChallengeRecordService;
import org.springframework.stereotype.Service;

/**
 * @author lanyiping
 * @description 针对表【challenge_record(挑战记录表)】的数据库操作Service实现
 * @createDate 2025-03-25 00:23:12
 */
@Service
public class ChallengeRecordServiceImpl extends ServiceImpl<ChallengeRecordMapper, ChallengeRecord>
        implements ChallengeRecordService {

    @Override
    public Boolean collectCoins(ChallengeRecord challengeRecord) {
        return this.save(challengeRecord);
    }
}





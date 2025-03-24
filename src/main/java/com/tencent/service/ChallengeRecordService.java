package com.tencent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.model.ChallengeRecord;

/**
 * @author lanyiping
 * @description 针对表【challenge_record(挑战记录表)】的数据库操作Service
 * @createDate 2025-03-25 00:23:12
 */
public interface ChallengeRecordService extends IService<ChallengeRecord> {

    Boolean collectCoins(ChallengeRecord challengeRecord);
}

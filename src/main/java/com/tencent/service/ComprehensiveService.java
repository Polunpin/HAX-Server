package com.tencent.service;

import com.tencent.response.ChallengeResponse;

/**
 * @author lanyiping
 * @description 综合：混合其他类的接口
 */
public interface ComprehensiveService {

    ChallengeResponse getChallengeList(String userId);

}

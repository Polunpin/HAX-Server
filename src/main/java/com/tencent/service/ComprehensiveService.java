package com.tencent.service;

import com.tencent.model.KnowledgeLibrary;
import com.tencent.request.KnowledgeLibraryRequest;
import com.tencent.response.ChallengeResponse;
import com.tencent.response.PracticeRecordsResponse;
import com.tencent.response.PracticeResponse;
import com.tencent.response.UserResponse;

import java.util.List;

/**
 * @author lanyiping
 * @description 综合：混合其他类的接口
 */
public interface ComprehensiveService {

    UserResponse getLearningProgress(String userId);

    List<KnowledgeLibrary> continueLearning(KnowledgeLibraryRequest knowledgeLibraryRequest);

    List<PracticeResponse> getPracticeList(String userId);

    List<PracticeRecordsResponse> getPracticeRecordList(String userId);

    ChallengeResponse getChallengeList(String userId);

}

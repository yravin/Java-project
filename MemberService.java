package com.learnapi.test.service;

import com.learnapi.test.dto.MemberRequest;
import com.learnapi.test.dto.MemberResponse;

import java.util.List;

public interface MemberService {
    void create(MemberRequest memberRequest);
    void update(Long id,MemberRequest memberRequest);
    void delete(Long id);
    MemberResponse getById(Long id);
    List<MemberResponse> getAll();
}

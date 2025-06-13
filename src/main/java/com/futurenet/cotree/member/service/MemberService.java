package com.futurenet.cotree.member.service;

import com.futurenet.cotree.member.dto.request.MemberAgeAndGenderRequest;
import com.futurenet.cotree.member.dto.request.MemberInfoUpdateRequest;


public interface MemberService {
    void updateMemberInfo(MemberInfoUpdateRequest request, Long memberId);
    void updateMemberAgeAndGender(MemberAgeAndGenderRequest request, Long memberId);
}

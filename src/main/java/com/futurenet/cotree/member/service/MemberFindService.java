package com.futurenet.cotree.member.service;

import com.futurenet.cotree.member.domain.Member;
import com.futurenet.cotree.member.dto.response.MyPageResponse;

public interface MemberFindService {
    Member getMemberByEmail(String email);
    MyPageResponse getMyPageInfo(Long id);
}

package com.futurenet.cotree.member.service;

import com.futurenet.cotree.member.domain.Member;

public interface MemberFindService {
    Member getMemberByEmail(String email);
}

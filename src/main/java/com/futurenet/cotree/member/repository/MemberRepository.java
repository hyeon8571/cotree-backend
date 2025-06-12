package com.futurenet.cotree.member.repository;

import com.futurenet.cotree.member.domain.Member;
import com.futurenet.cotree.member.dto.request.OAuthSignupRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberRepository {
    Member getMemberByEmail(String email);
    int saveOAuthMember(@Param("request") OAuthSignupRequest oAuthSignupRequest);
}

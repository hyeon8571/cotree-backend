package com.futurenet.cotree.member.repository;

import com.futurenet.cotree.member.domain.Member;
import com.futurenet.cotree.member.dto.request.OAuthSignupRequest;
import com.futurenet.cotree.member.dto.response.MemberGenderAgeResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberRepository {
    Member getMemberByEmail(String email);
    int saveOAuthMember(@Param("request") OAuthSignupRequest oAuthSignupRequest);
    MemberGenderAgeResponse getMemberGenderAge(@Param("id") Long id);
}

package com.futurenet.cotree.member.service;

import com.futurenet.cotree.member.dto.request.OAuthSignupRequest;
import com.futurenet.cotree.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberAuthServiceImpl implements MemberAuthService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void registerMember(OAuthSignupRequest oAuthSignupRequest) {
        memberRepository.saveOAuthMember(oAuthSignupRequest);
    }
}

package com.futurenet.cotree.member.service;

import com.futurenet.cotree.member.dto.request.OAuthSignupRequest;
import com.futurenet.cotree.member.repository.MemberRepository;
import com.futurenet.cotree.member.service.exception.MemberErrorCode;
import com.futurenet.cotree.member.service.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberAuthServiceImpl implements MemberAuthService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long registerMember(OAuthSignupRequest oAuthSignupRequest) {
        int result = memberRepository.saveOAuthMember(oAuthSignupRequest);

        if (result == 0) {
            throw new MemberException(MemberErrorCode.SIGNUP_FAIL);
        }
        
        return oAuthSignupRequest.getMemberId();
    }
}

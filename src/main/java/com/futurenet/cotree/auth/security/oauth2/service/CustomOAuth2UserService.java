package com.futurenet.cotree.auth.security.oauth2.service;

import com.futurenet.cotree.auth.security.dto.UserAuthDto;
import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.auth.security.oauth2.dto.response.KakaoResponse;
import com.futurenet.cotree.auth.security.oauth2.dto.response.OAuth2Response;
import com.futurenet.cotree.member.domain.Member;
import com.futurenet.cotree.member.dto.request.OAuthSignupRequest;
import com.futurenet.cotree.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());

        Member member = memberRepository.getMemberByEmail(oAuth2Response.getEmail());

        if (member == null) {
            OAuthSignupRequest oAuthSignupRequest = OAuthSignupRequest.from(oAuth2Response);
            memberRepository.saveOAuthMember(oAuthSignupRequest);
            member = memberRepository.getMemberByEmail(oAuthSignupRequest.getEmail());
        }

        UserAuthDto userAuthDto = new UserAuthDto(member.getId(), "ROLE_USER");
        return new UserPrincipal(userAuthDto);
    }
}

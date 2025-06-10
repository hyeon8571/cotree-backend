package com.futurenet.cotree.auth.oauth2.service;

import com.futurenet.cotree.auth.oauth2.dto.response.KakaoResponse;
import com.futurenet.cotree.auth.oauth2.dto.response.OAuth2Response;
import com.futurenet.cotree.auth.security.dto.UserAuthDto;
import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.member.domain.Member;
import com.futurenet.cotree.member.dto.request.OAuthSignupRequest;
import com.futurenet.cotree.member.service.MemberFindService;
import com.futurenet.cotree.member.service.SignupFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final SignupFacadeService signupFacadeService;
    private final MemberFindService memberFindService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2Response oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        Member member = memberFindService.getMemberByEmail(oAuth2Response.getEmail());

        if (member == null) {
            OAuthSignupRequest oAuthSignupRequest = OAuthSignupRequest.from(oAuth2Response);
            signupFacadeService.signup(oAuthSignupRequest);
            member = memberFindService.getMemberByEmail(oAuthSignupRequest.getEmail());
        }

        UserAuthDto userAuthDto = new UserAuthDto(member.getId(), "ROLE_USER");
        return new UserPrincipal(userAuthDto);
    }
}

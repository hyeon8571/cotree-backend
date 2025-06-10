package com.futurenet.cotree.member.dto.request;

import com.futurenet.cotree.auth.security.oauth2.dto.response.OAuth2Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OAuthSignupRequest {
    private String email;
    private String provider;
    private String nickname;
    private String profileImage;

    public static OAuthSignupRequest from(OAuth2Response oAuth2Response) {
        return OAuthSignupRequest.builder()
                .email(oAuth2Response.getEmail())
                .provider(oAuth2Response.getProvider())
                .nickname(oAuth2Response.getNickname())
                .profileImage(oAuth2Response.getProfileImage())
                .build();
    }
}

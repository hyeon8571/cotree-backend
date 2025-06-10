package com.futurenet.cotree.auth.oauth2.dto.response;

public interface OAuth2Response {
    String getProvider();
    String getEmail();
    String getNickname();
    String getProfileImage();
}

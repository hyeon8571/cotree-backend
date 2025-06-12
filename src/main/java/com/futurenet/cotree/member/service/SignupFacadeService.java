package com.futurenet.cotree.member.service;

import com.futurenet.cotree.member.dto.request.OAuthSignupRequest;

public interface SignupFacadeService {
    Long signup(OAuthSignupRequest oAuthSignupRequest);
}

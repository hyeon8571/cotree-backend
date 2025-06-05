package com.futurenet.cotree.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RefreshTokenSaveRequest {
    private String refreshToken;
    private Long memberId;
}

package com.futurenet.cotree.auth.service.exception;

import com.futurenet.cotree.global.exception.RestApiException;

public class RefreshTokenException extends RestApiException {
    public RefreshTokenException() {
        super(AuthErrorCode.REFRESH_ERROR);
    }
}

package com.futurenet.cotree.auth.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import com.futurenet.cotree.global.exception.RestApiException;

public class AuthException extends RestApiException {
    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}

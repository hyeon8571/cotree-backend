package com.futurenet.cotree.auth.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorCode {

    REFRESH_ERROR("AU003", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final HttpStatus httpStatus;
}

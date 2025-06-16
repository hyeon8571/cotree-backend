package com.futurenet.cotree.admin.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AdminErrorCode implements ErrorCode {
    INVALID_RANGE("AD001", HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIAL("AD002", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final HttpStatus httpStatus;
}

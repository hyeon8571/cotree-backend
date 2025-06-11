package com.futurenet.cotree.greenpoint.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GreenPointErrorCode implements ErrorCode {
    NOT_ENOUGH_POINT("GP001", HttpStatus.BAD_REQUEST),
    GREEN_POINT_INSERT_FAILED("GP002", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final HttpStatus httpStatus;
}

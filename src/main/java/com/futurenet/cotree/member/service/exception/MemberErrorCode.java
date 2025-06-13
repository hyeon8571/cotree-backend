package com.futurenet.cotree.member.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    SIGNUP_FAIL("AU000", HttpStatus.UNAUTHORIZED),
    MEMBER_NOT_FOUND("AU002", HttpStatus.BAD_REQUEST);

    private final String code;
    private final HttpStatus httpStatus;
}

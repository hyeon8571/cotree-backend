package com.futurenet.cotree.member.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import com.futurenet.cotree.global.exception.RestApiException;

public class MemberException extends RestApiException {

    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}

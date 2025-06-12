package com.futurenet.cotree.greenpoint.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import com.futurenet.cotree.global.exception.RestApiException;

public class GreenPointException extends RestApiException {
    public GreenPointException(ErrorCode errorCode) {
        super(errorCode);
    }
}

package com.futurenet.cotree.payment.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import com.futurenet.cotree.global.exception.RestApiException;

public class PaymentException extends RestApiException {

    public PaymentException(ErrorCode errorCode) {
        super(errorCode);
    }
}

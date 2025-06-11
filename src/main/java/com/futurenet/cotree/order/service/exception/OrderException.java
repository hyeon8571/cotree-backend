package com.futurenet.cotree.order.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import com.futurenet.cotree.global.exception.RestApiException;

public class OrderException extends RestApiException {

    public OrderException(ErrorCode errorCode) {
        super(errorCode);
    }
}

package com.futurenet.cotree.order.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import com.futurenet.cotree.global.exception.RestApiException;
import lombok.Getter;

@Getter
public class OrderException extends RestApiException {

    private final ErrorCode errorCode;

    public OrderException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}

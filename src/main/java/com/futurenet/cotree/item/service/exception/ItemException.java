package com.futurenet.cotree.item.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import com.futurenet.cotree.global.exception.RestApiException;

public class ItemException extends RestApiException {
    public ItemException(ErrorCode errorCode) {
        super(errorCode);
    }
}

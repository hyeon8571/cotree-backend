package com.futurenet.cotree.tree.service.exception;

import com.futurenet.cotree.global.exception.RestApiException;

public class InvalidExpValueException extends RestApiException {
    public InvalidExpValueException() {
        super(TreeErrorCode.INVALID_EXP_VALUE);
    }
}

package com.futurenet.cotree.tree.service.exception;

import com.futurenet.cotree.global.exception.RestApiException;

public class InvalidActionException extends RestApiException {
    public InvalidActionException() {
        super(TreeErrorCode.INVALID_ACTION);
    }
}

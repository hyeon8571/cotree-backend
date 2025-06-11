package com.futurenet.cotree.tree.service.exception;

import com.futurenet.cotree.global.exception.RestApiException;

public class TreeCreateFailedException extends RestApiException {
    public TreeCreateFailedException(String message) {
        super(TreeErrorCode.TREE_CREATE_FAILED);
    }
}

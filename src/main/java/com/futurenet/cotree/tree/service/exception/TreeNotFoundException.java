package com.futurenet.cotree.tree.service.exception;

import com.futurenet.cotree.global.exception.RestApiException;

public class TreeNotFoundException extends RestApiException {
    public TreeNotFoundException() {
        super(TreeErrorCode.TREE_NOT_FOUND);
    }
}

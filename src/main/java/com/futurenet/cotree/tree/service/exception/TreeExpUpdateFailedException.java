package com.futurenet.cotree.tree.service.exception;

import com.futurenet.cotree.global.exception.RestApiException;

public class TreeExpUpdateFailedException extends RestApiException {
    public TreeExpUpdateFailedException() {
        super(TreeErrorCode.TREE_EXP_UPDATE_FAILED);
    }
}

package com.futurenet.cotree.tree.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TreeErrorCode implements ErrorCode {
    TREE_NOT_FOUND("TR001", HttpStatus.NOT_FOUND),
    TREE_CREATE_FAILED("TR002", HttpStatus.INTERNAL_SERVER_ERROR),
    TREE_EXP_UPDATE_FAILED("TR003", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_EXP_VALUE("TR004", HttpStatus.BAD_REQUEST);

    private final String code;
    private final HttpStatus httpStatus;
}

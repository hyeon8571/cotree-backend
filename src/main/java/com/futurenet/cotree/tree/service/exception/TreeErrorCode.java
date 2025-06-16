package com.futurenet.cotree.tree.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TreeErrorCode implements ErrorCode {
    TREE_NOT_FOUND("TR001", HttpStatus.NOT_FOUND),
    TREE_EXP_UPDATE_FAILED("TR002", HttpStatus.INTERNAL_SERVER_ERROR),
    POINT_LACK("TR003", HttpStatus.BAD_REQUEST),
    INVALID_ACTION("TR004", HttpStatus.BAD_REQUEST),
    MAX_EXP_REACHED("TR005", HttpStatus.CONFLICT),
    UNAUTHORIZED_SUMMARY_REQUEST("TR006", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final HttpStatus httpStatus;
}

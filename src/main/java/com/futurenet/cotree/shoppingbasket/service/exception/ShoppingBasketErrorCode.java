package com.futurenet.cotree.shoppingbasket.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ShoppingBasketErrorCode implements ErrorCode {

    NOT_FOUND("SB001", HttpStatus.NOT_FOUND),
    ITEM_NOT_FOUND("SB002", HttpStatus.NOT_FOUND),
    ITEM_ALREADY_EXISTS("SB003", HttpStatus.CONFLICT),
    INVALID_ITEM_ID("SB004", HttpStatus.BAD_REQUEST),
    INVALID_QUANTITY("SB005", HttpStatus.BAD_REQUEST),
    ITEM_NOT_BELONG_TO_BASKET("SB006", HttpStatus.FORBIDDEN),
    INSERT_FAILED("SB007", HttpStatus.INTERNAL_SERVER_ERROR),
    UPDATE_FAILED("SB008", HttpStatus.INTERNAL_SERVER_ERROR),
    DELETE_FAILED("SB009", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final HttpStatus httpStatus;
}

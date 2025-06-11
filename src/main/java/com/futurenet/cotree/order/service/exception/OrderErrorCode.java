package com.futurenet.cotree.order.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OrderErrorCode implements ErrorCode {

    ORDER_REGISTER_FAIL("OR000", HttpStatus.BAD_REQUEST),
    ORDER_ITEM_REGISTER_FAIL("OR001", HttpStatus.BAD_REQUEST);

    private final String code;
    private final HttpStatus httpStatus;
}

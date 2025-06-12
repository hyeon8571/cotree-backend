package com.futurenet.cotree.item.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ItemErrorCode implements ErrorCode {

    ITEM_QUANTITY_LACK("IT000", HttpStatus.BAD_REQUEST);

    private final String code;
    private final HttpStatus httpStatus;
}

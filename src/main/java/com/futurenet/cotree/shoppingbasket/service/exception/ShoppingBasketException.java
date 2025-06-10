package com.futurenet.cotree.shoppingbasket.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ShoppingBasketException extends RuntimeException {
    private final ErrorCode errorCode;

    public ShoppingBasketException(ErrorCode errorCode) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
    }
}




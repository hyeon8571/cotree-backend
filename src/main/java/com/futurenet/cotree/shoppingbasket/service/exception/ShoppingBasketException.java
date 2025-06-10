package com.futurenet.cotree.shoppingbasket.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import com.futurenet.cotree.global.exception.RestApiException;
import lombok.Getter;

@Getter
public class ShoppingBasketException extends RestApiException {
    private final ErrorCode errorCode;

    public ShoppingBasketException(ShoppingBasketErrorCode shoppingBasketErrorCode) {
        super(shoppingBasketErrorCode);
        this.errorCode = shoppingBasketErrorCode;
    }
}




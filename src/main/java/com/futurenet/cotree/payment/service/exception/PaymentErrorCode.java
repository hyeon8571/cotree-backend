package com.futurenet.cotree.payment.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PaymentErrorCode implements ErrorCode {

    PAYMENT_FAIL("PA000", HttpStatus.BAD_REQUEST);

    private final String code;
    private final HttpStatus httpStatus;
}

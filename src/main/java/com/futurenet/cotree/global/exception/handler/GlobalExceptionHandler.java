package com.futurenet.cotree.global.exception.handler;

import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.global.exception.ErrorCode;
import com.futurenet.cotree.global.exception.RestApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<?> handleRestApiException(RestApiException e) {
        ErrorCode ec = e.getErrorCode();
        return new ResponseEntity<>(new ApiResponse<>(ec.getCode(), null), ec.getHttpStatus());
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<?> handlerMethodValidationException(HandlerMethodValidationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiResponse<>("VE000", null), HttpStatus.BAD_REQUEST);
    }
}

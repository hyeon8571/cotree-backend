package com.futurenet.cotree.admin.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import com.futurenet.cotree.global.exception.RestApiException;
import lombok.Getter;

@Getter
public class AdminException extends RestApiException {
    private final ErrorCode errorCode;

    public AdminException(AdminErrorCode adminErrorCode) {
        super(adminErrorCode);
        this.errorCode = adminErrorCode;
    }
}


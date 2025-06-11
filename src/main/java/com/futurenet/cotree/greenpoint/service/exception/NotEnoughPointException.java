package com.futurenet.cotree.greenpoint.service.exception;

import com.futurenet.cotree.global.exception.RestApiException;

public class NotEnoughPointException extends RestApiException {
  public NotEnoughPointException() {
    super(GreenPointErrorCode.NOT_ENOUGH_POINT);
  }
}

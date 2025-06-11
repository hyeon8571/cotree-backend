package com.futurenet.cotree.greenpoint.service.exception;

import com.futurenet.cotree.global.exception.RestApiException;

public class GreenPointInsertFailedException extends RestApiException {
  public GreenPointInsertFailedException() {
    super(GreenPointErrorCode.GREEN_POINT_INSERT_FAILED);
  }
}

package com.futurenet.cotree.tree.service.exception;

import com.futurenet.cotree.global.exception.RestApiException;

public class PointLackException extends RestApiException {
  public PointLackException() {
    super(TreeErrorCode.POINT_LACK);
  }
}

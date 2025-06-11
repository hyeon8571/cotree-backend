package com.futurenet.cotree.tree.service.exception;

import com.futurenet.cotree.global.exception.RestApiException;

public class MaxExpReachedException extends RestApiException {
  public MaxExpReachedException() {
    super(TreeErrorCode.MAX_EXP_REACHED);
  }
}

package com.futurenet.cotree.tree.service.exception;

import com.futurenet.cotree.global.exception.ErrorCode;
import com.futurenet.cotree.global.exception.RestApiException;
import lombok.Getter;

@Getter
public class TreeException extends RestApiException {
  private final ErrorCode errorCode;

  public TreeException(TreeErrorCode treeErrorCode) {
      super(treeErrorCode);
      this.errorCode = treeErrorCode;
  }
}

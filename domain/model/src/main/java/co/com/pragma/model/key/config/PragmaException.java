package co.com.pragma.model.key.config;

import lombok.Getter;

@Getter
public class PragmaException extends RuntimeException {
  private final ErrorCode error;

  public PragmaException(ErrorCode errorCode) {
    super(errorCode.getDetail());
    this.error = errorCode;
  }
}

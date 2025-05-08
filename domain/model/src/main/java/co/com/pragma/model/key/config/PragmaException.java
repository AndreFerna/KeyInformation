package co.com.pragma.model.key.config;

public class PragmaException extends RuntimeException {
  private final ErrorCode error;

  public PragmaException(ErrorCode errorCode) {
    super(errorCode.getDetail());
    this.error = errorCode;
  }
}

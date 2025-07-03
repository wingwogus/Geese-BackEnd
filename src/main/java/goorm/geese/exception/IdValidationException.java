package goorm.geese.exception;


public class IdValidationException extends RuntimeException {
    public IdValidationException(String message) {
      super(message);
    }
}

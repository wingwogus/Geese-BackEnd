package goorm.geese.exception;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException(String message) {
        super(ErrorCode.INVALID_TOKEN, message);
    }
}

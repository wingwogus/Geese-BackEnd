package goorm.geese.exception;

public class NotFoundCardException extends BusinessException {
    public NotFoundCardException() {
        super(ErrorCode.NOT_FOUND_CARD, "카드를 찾을 수 없습니다.");
    }
}

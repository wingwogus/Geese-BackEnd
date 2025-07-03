package goorm.geese.exception;

public class NotFoundCommentException extends BusinessException {
    public NotFoundCommentException() {
        super(ErrorCode.NOT_FOUND_COMMENT, "댓글을 찾을 수 없습니다.");
    }
}

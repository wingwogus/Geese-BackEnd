package goorm.geese.exception;

public class NotFoundDeviceException extends BusinessException {
    public NotFoundDeviceException() {
        super(ErrorCode.NOT_FOUND_DEVICE, "디바이스를 찾을 수 없습니다.");
    }
}

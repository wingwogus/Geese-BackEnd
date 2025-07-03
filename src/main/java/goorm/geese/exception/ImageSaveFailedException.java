package goorm.geese.exception;


public class ImageSaveFailedException extends BusinessException {
    public ImageSaveFailedException() {
        super(ErrorCode.IMAGE_SAVE_ERROR, "이미지 저장에 실패했습니다.");
    }

}

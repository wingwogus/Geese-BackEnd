package goorm.geese.dto.auth;

import goorm.geese.domain.entity.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SignUpRequestDto {

    @Schema(description = "회원가입 id", example = "user1@naver.com")
    private String username;

    @Schema(description = "닉네임", example = "거인이재현")
    private String nickname;

    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @Schema(description = "휴대폰 번호", example = "01012345678")
    private String phone;

    @Schema(description = "프로필 사진", example = "example.png")
    private String profileImg;

    @Schema(description = "성별", example = "MALE, FEMALE")
    private Gender gender;
}

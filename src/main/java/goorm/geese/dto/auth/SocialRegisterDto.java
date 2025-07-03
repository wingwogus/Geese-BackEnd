package goorm.geese.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import goorm.geese.domain.entity.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class SocialRegisterDto {

    @Schema(description = "닉네임", example = "소셜유저닉")
    private String nickname;

    @Schema(description = "휴대폰 번호", example = "01012345678")
    private String phone;

    @Schema(description = "성별", example = "MALE, FEMALE")
    private Gender gender;

    @JsonFormat(pattern = "yyyy-MM-dd") // 반드시 필요
    private LocalDate birthdate;
}
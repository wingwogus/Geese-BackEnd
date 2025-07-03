package goorm.geese.dto.post;

import goorm.geese.domain.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SimpleMemberInfoDto {

    @Schema(description = "닉네임")
    String nickname;

    @Schema(description = "프로필 이미지")
    String profileImg;

    public static SimpleMemberInfoDto from(Member member) {
        return SimpleMemberInfoDto.builder()
                .nickname(member.getNickname())
                .profileImg(member.getProfileImg())
                .build();

    }
}

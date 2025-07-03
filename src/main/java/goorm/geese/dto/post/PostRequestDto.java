package goorm.geese.dto.post;

import goorm.geese.domain.entity.Device;
import goorm.geese.domain.entity.Member;
import goorm.geese.domain.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostRequestDto {

    @Schema(description = "게시물 제목", example = "콘서트 동행 구해요")
    private String title;

    @Schema(description = "게시물 내용", example = "남자 여자 아무나 상관없습니다")
    private String content;

    @Schema(description = "게시물 사진", example = "photo.png")
    private String photo;

    @Schema(description = "동행 예정 시각", example = "2024.11.11")
    private LocalDateTime meetingTime;

    @Schema(description = "디바이스", example = "GALAXY S25")
    private Long deviceId;

    public Post toEntity(Member member, Device device) {
        Post post = Post.builder()
                .title(this.title)
                .content(this.content)
                .device(device)
                .build();

        // 연관관계 설정
        post.setAuthor(member);
        return post;
    }

    public void updatePost(Post post, Device device) {
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setDevice(device);
    }
}

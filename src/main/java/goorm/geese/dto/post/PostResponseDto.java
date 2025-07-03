package goorm.geese.dto.post;

import goorm.geese.domain.entity.Post;
import goorm.geese.dto.comment.CommentResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostResponseDto {
    @Schema(description = "게시물 ID", example = "1")
    private Long id;

    @Schema(description = "게시물 제목")
    private String title;

    @Schema(description = "게시물 내용")
    private String content;

    @Schema(description = "작성자")
    private SimpleMemberInfoDto author;

    @Schema(description = "디바이스")
    private String device;

    @Schema(description = "게시물 생성 시간")
    private LocalDateTime createdAt;

    @Schema(description = "게시물 최종 수정 시간")
    private LocalDateTime lastModifiedAt;

    @Schema(description = "현재 로그인한 사용자가 이 게시물을 좋아요 눌렀는지 여부")
    private boolean liked;

    @Schema(description = "게시물의 좋아요 개수")
    private int likesCount;

    @Schema(description = "게시물 조회수")
    private int viewCount;

    @Schema(description = "게시물 댓글 수")
    private int commentCount;

    public static PostResponseDto from(Post post, boolean liked) {
        SimpleMemberInfoDto author = SimpleMemberInfoDto.from(post.getAuthor());

        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(author)
                .device(post.getDevice().getName())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .liked(liked)
                .likesCount(post.getPostLikes().size())
                .viewCount(post.getViewCount())
                .commentCount(post.getComments().size())
                .build();
    }
}

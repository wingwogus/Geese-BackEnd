package goorm.geese.dto.comment;

import goorm.geese.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private Long id;

    private String content;

    private String memberNickname;

    private String postTitle;

    private LocalDateTime createdAt;

    private List<CommentResponseDto> children;

    public static CommentResponseDto from(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .memberNickname(comment.getMember().getNickname())
                .postTitle(comment.getPost().getTitle())
                .createdAt(comment.getCreatedAt())
                .children(comment.getRecomments().stream()
                        .map(CommentResponseDto::from)
                        .toList())
                .build();
    }
}

package goorm.geese.dto.comment;

import lombok.Data;


@Data
public class CommentRequestDto {
    private Long postId;
    private String content;
    private Long parentId;
}

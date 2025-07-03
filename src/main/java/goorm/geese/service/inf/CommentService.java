package goorm.geese.service.inf;

import goorm.geese.domain.entity.Comment;
import goorm.geese.domain.entity.Post;
import goorm.geese.dto.comment.CommentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    void createComment(String content, Long memberId, Long postId);

    void createRecomment(String content, Long memberId, Long commentId);

    void deleteComment(Long commentId, Long memberId);

    void updateComment(String content, Long memberId, Long commentId);

    List<CommentResponseDto> findAllByPost(Post post);
}

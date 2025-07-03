package goorm.geese.service.impl;

import goorm.geese.domain.entity.Comment;
import goorm.geese.domain.entity.Member;
import goorm.geese.domain.entity.Post;
import goorm.geese.dto.comment.CommentResponseDto;
import goorm.geese.exception.InvalidTokenException;
import goorm.geese.exception.NotFoundCommentException;
import goorm.geese.exception.NotFoundMemberException;
import goorm.geese.exception.NotFoundPostException;
import goorm.geese.repository.CommentRepository;
import goorm.geese.repository.MemberRepository;
import goorm.geese.repository.PostRepository;
import goorm.geese.service.inf.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    public void createComment(String content, Long memberId, Long postId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);

        commentRepository.save(Comment.createComment(member, post, content));
    }

    @Override
    public void createRecomment(String content, Long memberId, Long commentId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(NotFoundCommentException::new);

        commentRepository.save(Comment.createReply(member, comment.getPost(), comment, content));
    }


    @Override
    public void deleteComment(Long commentId, Long memberId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(NotFoundCommentException::new);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        validateAuth(comment, member);

        commentRepository.deleteById(commentId);
    }

    @Override
    public void updateComment(String content, Long memberId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(NotFoundCommentException::new);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        validateAuth(comment, member);

        comment.updateComment(content);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentResponseDto> findAllByPost(Post post) {
        return commentRepository.findRootCommentsByPost(post).stream()
                .map(CommentResponseDto::from)
                .toList();
    }

    public void validateAuth(Comment comment, Member member) {
        if (!(comment.getPost().getAuthor().equals(member) && comment.getMember().equals(member))) {
            throw new InvalidTokenException("댓글 작성자 혹은 게시글 작성자만 수정/삭제할 수 있습니다.");
        }
    }
}

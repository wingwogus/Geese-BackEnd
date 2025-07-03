package goorm.geese.controller;

import goorm.geese.domain.entity.Post;
import goorm.geese.dto.ApiResponse;
import goorm.geese.dto.auth.CustomUserDetails;
import goorm.geese.dto.comment.CommentRequestDto;
import goorm.geese.dto.comment.CommentResponseDto;
import goorm.geese.service.inf.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Comment", description = "댓글 API")
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성", description = "특정 게시판에 댓글을 작성합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createComment(
            @RequestBody CommentRequestDto dto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (dto.getParentId() != null) {
            commentService.createRecomment(dto.getContent(), userDetails.getMember().getId(), dto.getParentId());
        } else {
            commentService.createComment(dto.getContent(), userDetails.getMember().getId(), dto.getPostId());
        }

        return ResponseEntity.ok(ApiResponse.success("댓글 작성 성공", null));
    }

    @Operation(summary = "댓글 삭제", description = "특정 댓글을 작성합니다.")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails != null ? userDetails.getMember().getId() : null;

        commentService.deleteComment(commentId, memberId);

        return ResponseEntity.ok(ApiResponse.success("댓글 삭제 성공", null));
    }

    @Operation(summary = "댓글 수정", description = "특정 댓글을 수정합니다.")
    @PutMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto dto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails != null ? userDetails.getMember().getId() : null;

        commentService.updateComment(dto.getContent(), commentId, memberId);

        return ResponseEntity.ok(ApiResponse.success("댓글 수정 성공", null));
    }

    @Operation(summary = "댓글 작성", description = "특정 게시판에 댓글을 조회합니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CommentResponseDto>>> getCommentByPost(
            @RequestParam("postId") Post post) {
        return ResponseEntity.ok(ApiResponse.success("게시글 댓글 조회 성공", commentService.findAllByPost(post)));
    }
}

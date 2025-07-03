package goorm.geese.controller;

import goorm.geese.domain.entity.Device;
import goorm.geese.domain.entity.Post;
import goorm.geese.dto.ApiResponse;
import goorm.geese.dto.auth.CustomUserDetails;
import goorm.geese.dto.post.PostLikeResponseDto;
import goorm.geese.dto.post.PostRequestDto;
import goorm.geese.dto.post.PostResponseDto;
import goorm.geese.dto.post.SearchRequest;
import goorm.geese.service.inf.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Post", description = "포스트 API")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "모든 게시글 조회", description = "현재 생성된 모든 게시글을 조횝합니다")
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getAllPosts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails != null ? userDetails.getMember().getId() : null;

        return ResponseEntity.ok(ApiResponse.success("모든 게시글 조회 성공", postService.getAllPosts(memberId)));
    }

    @Operation(summary = "게시글 생성", description = "게시글 하나를 생성합니다")
    @PostMapping("")
    public ResponseEntity<ApiResponse<Void>> createPost(@RequestBody PostRequestDto dto, @AuthenticationPrincipal CustomUserDetails userDetails) {
        postService.createPost(dto, userDetails.getMember().getId());
        return ResponseEntity.ok(ApiResponse.success("게시글 생성 성공", null));
    }

    @Operation(summary = "게시글 수정", description = "게시글 하나를 수정합니다")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePost(@PathVariable("id") Post post, @RequestBody PostRequestDto dto, @AuthenticationPrincipal CustomUserDetails userDetails) {
        postService.updatePost(post, dto, userDetails.getMember().getId());
        return ResponseEntity.ok(ApiResponse.success("게시글 수정 성공", null));
    }

    @Operation(summary = "게시글 삭제", description = "게시글 하나를 삭제합니다")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable("id") Post post, @AuthenticationPrincipal CustomUserDetails userDetails) {
        postService.deletePost(post, userDetails.getMember().getId());
        return ResponseEntity.ok(ApiResponse.success("게시글 삭제 성공", null));
    }

    // 게시글 단건 조회
    @Operation(summary = "게시글 조회", description = "게시글 하나를 조회합니다")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponseDto>> getPostDetail(@PathVariable("id") Long postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails != null ? userDetails.getMember().getId() : null;

        return ResponseEntity.ok(ApiResponse.success(
                "게시글 조회 성공",
                postService.getPostDetail(postId, memberId)));
    }

    @Operation(summary = "멤버별 게시글 조회", description = "특정 멤버가 작성한 모든 게시물을 조회합니다")
    @GetMapping("/member/{nickname}")
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getPostsByMember(@PathVariable("nickname") String nickname) {

        return ResponseEntity.ok(ApiResponse.success(
                "멤버별 게시글 조회 성공",
                postService.getPostsByMember(nickname)));
    }

    // 디바이스별 게시글 조회
    @Operation(summary = "디바이스별 게시글 조회", description = "원하는 디바이스의 모든 게시글을 조회합니다")
    @GetMapping("/device/{id}")
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getPostsByDevice(@PathVariable("id") Device device, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails != null ? userDetails.getMember().getId() : null;

        return ResponseEntity.ok(ApiResponse.success(
                "디바이스별 게시글 조회 성공",
                postService.getPostsByDevice(device, memberId)));
    }

    // 날짜별 게시글 조회
    @Operation(summary = "날짜별 게시글 조회", description = "원하는 날짜의 모든 게시글을 조회합니다")
    @GetMapping("/date")
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getPostsByDate(@RequestBody SearchRequest searchRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails != null ? userDetails.getMember().getId() : null;

        List<PostResponseDto> posts = postService.getPostsByDateRange(
                searchRequest.getFrom(),
                searchRequest.getTo(),
                memberId);

        return ResponseEntity.ok(ApiResponse.success("날짜별 게시글 조회 성공", posts));
    }

    // 좋아요 게시물 목록
    @Operation(summary = "좋아요 게시글 조회", description = "좋아요 누른 게시물을 조회합니다")
    @GetMapping("/like")
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getPostByLike(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails != null ? userDetails.getMember().getId() : null;

        List<PostResponseDto> likedPost = postService.getLikedPost(memberId);
        return ResponseEntity.ok(ApiResponse.success("좋아요 누른 게시물 조회 성공", likedPost));
    }

    // 포스트 좋아요
    @Operation(summary = "게시글 좋아요", description = "게시글 하나에 좋아요을 누르거나 취소합니다")
    @PostMapping("/{id}/like")
    public ResponseEntity<ApiResponse<PostLikeResponseDto>> togglePostLike(@PathVariable("id") Post post,
                                                                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        PostLikeResponseDto response =
                postService.togglePostLike(post, userDetails.getMember().getId());
        return ResponseEntity.ok(ApiResponse.success("좋아요/취소 성공", response));
    }

//    // 게시글 키워드 검색
//    @GetMapping("/search")
//    public List<PostResponseDto> searchPosts(@RequestParam("keyword") String keyword) {
//        return postService.searchPosts(keyword);
//    }



}

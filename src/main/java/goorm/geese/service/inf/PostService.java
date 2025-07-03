package goorm.geese.service.inf;

import goorm.geese.domain.entity.Device;
import goorm.geese.domain.entity.Member;
import goorm.geese.domain.entity.Post;
import goorm.geese.dto.post.PostLikeResponseDto;
import goorm.geese.dto.post.PostRequestDto;
import goorm.geese.dto.post.PostResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PostService {
    // 포스트 생성
    void createPost(PostRequestDto post, Long memberId);

    // 포스트 업데이트
    void updatePost(Post post, PostRequestDto requestDto, Long memberId);

    // 포스트 삭제
    void deletePost(Post post, Long memberId);

    @Transactional(readOnly = true)
    List<PostResponseDto> getPostsByDevice(Device device, Long memberId);

    List<PostResponseDto> getLikedPost(Long memberId);

    // 모든 포스트 불러오기
    List<PostResponseDto> getAllPosts(Long memberId);

    // 기간별 포스트 불러오기
    List<PostResponseDto> getPostsByDateRange(LocalDateTime from, LocalDateTime to, Long memberId);

    // 포스트 좋아요 토글 기능
    PostLikeResponseDto togglePostLike(Post post, Long memberId);

    // 유저별 포스트 불러오기
    List<PostResponseDto> getPostsByMember(String nickname);

    // 포스트 조회 기능
    PostResponseDto getPostDetail(Long postId, Long memberId);
}

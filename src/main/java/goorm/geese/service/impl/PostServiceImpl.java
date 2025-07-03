package goorm.geese.service.impl;

import goorm.geese.domain.entity.*;
import goorm.geese.dto.post.PostLikeResponseDto;
import goorm.geese.dto.post.PostRequestDto;
import goorm.geese.dto.post.PostResponseDto;
import goorm.geese.exception.InvalidTokenException;
import goorm.geese.exception.NotFoundDeviceException;
import goorm.geese.exception.NotFoundMemberException;
import goorm.geese.exception.NotFoundPostException;
import goorm.geese.repository.*;
import goorm.geese.service.inf.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostViewRepository postViewRepository;
    private final DeviceRepository deviceRepository;

    @Override
    public void createPost(PostRequestDto dto, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        Device device = deviceRepository.findById(dto.getDeviceId())
                .orElseThrow(NotFoundDeviceException::new);

        Post post = dto.toEntity(member, device);

        postRepository.save(post);
    }

    @Override
    public void updatePost(Post post, PostRequestDto dto, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        Device device = deviceRepository.findById(dto.getDeviceId())
                .orElseThrow(NotFoundDeviceException::new);

        validateAuth(post, member);

        dto.updatePost(post, device);
    }

    @Override
    public void deletePost(Post post, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        validateAuth(post, member);
        postRepository.delete(post);
    }

    @Override
    public PostResponseDto getPostDetail(Long postId, Long memberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);

        if (memberId != null) {
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(NotFoundMemberException::new);

            boolean alreadyViewed = postViewRepository.existsByPostAndMember(post, member);

            if (!alreadyViewed) {
                post.increaseViewCount(); // Post에 정의한 조회수 증가 메서드
                postViewRepository.save(new PostView(post, member));
            }

            return PostResponseDto.from(post, isPostLikedByMember(post, memberId));
        }

        return PostResponseDto.from(post,false);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostResponseDto> getPostsByDevice(Device device, Long memberId) {
        return postRepository.findByDevice(device)
                .orElseThrow(NotFoundPostException::new)
                .stream()
                .map(post -> PostResponseDto.from(post, isPostLikedByMember(post, memberId)))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDto> getLikedPost(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        return postLikeRepository.findByMember(member)
                .orElseThrow(NotFoundPostException::new)
                .stream()
                .map(postLike -> PostResponseDto.from(postLike.getPost(), true))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostResponseDto> getAllPosts(Long memberId) {
        return postRepository.findAll().stream()
                .map(post -> PostResponseDto.from(post, isPostLikedByMember(post,memberId)))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostResponseDto> getPostsByDateRange(LocalDateTime from, LocalDateTime to, Long memberId) {
        return postRepository.findAllByCreatedAtBetween(from, to.plusDays(1))
                .orElseThrow(NotFoundPostException::new)
                .stream()
                .map(post -> PostResponseDto.from(post, isPostLikedByMember(post,memberId)))
                .collect(Collectors.toList());
    }

    @Override
    public PostLikeResponseDto togglePostLike(Post post, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        boolean liked;
        if (postLikeRepository.existsByMemberAndPost(member, post)) {
            postLikeRepository.deleteByMemberAndPost(member, post);
            liked = false;
        } else {
            PostLike postLike = new PostLike(member, post);
            postLikeRepository.save(postLike);
            liked = true;
        }

        int likeCount = postLikeRepository.countByPost(post);

        return new PostLikeResponseDto(liked, likeCount);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostResponseDto> getPostsByMember(String nickname) {
        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(NotFoundMemberException::new);

        return postRepository.findByAuthor(member)
                .orElseThrow(NotFoundPostException::new)
                .stream()
                .map(post -> PostResponseDto.from(post, isPostLikedByMember(post, member.getId())))
                .toList();
    }

    private void validateAuth(Post post, Member member) {
        if (!(post.getAuthor().equals(member))) {
            throw new InvalidTokenException("작성자만 수정/삭제할 수 있습니다.");
        }
    }

    // 사용자가 좋아요를 눌렀는지 안눌렀는지
    private boolean isPostLikedByMember(Post post, Long memberId) {
        if (memberId == null) return false;
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        return postLikeRepository.existsByMemberAndPost(member, post);
    }


//    @Override
//    public List<PostResponseDto> searchPosts(String keyword) {
//        return postRepository.findByTitleOrContentContaining(keyword).stream()
//                .map(PostResponseDto::from)
//                .collect(Collectors.toList());
//    }
}

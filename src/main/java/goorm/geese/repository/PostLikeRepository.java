package goorm.geese.repository;

import goorm.geese.domain.entity.Member;
import goorm.geese.domain.entity.Post;
import goorm.geese.domain.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByMemberAndPost(Member member, Post post);

    Optional<List<PostLike>> findByMember(Member member);

    int countByPost(Post post);

    boolean existsByMemberAndPost(Member member, Post post);

    void deleteByMemberAndPost(Member member, Post post);
}

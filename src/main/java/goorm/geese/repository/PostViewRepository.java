package goorm.geese.repository;
import goorm.geese.domain.entity.Member;
import goorm.geese.domain.entity.Post;
import goorm.geese.domain.entity.PostView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostViewRepository extends JpaRepository<PostView, Long> {
    boolean existsByPostAndMember(Post post, Member member);
}

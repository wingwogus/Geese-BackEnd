package goorm.geese.repository;

import goorm.geese.domain.entity.Comment;
import goorm.geese.domain.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @EntityGraph(attributePaths = {"post", "member"})
    List<Comment> findByPost(Post post);

    @EntityGraph(attributePaths = {"post"})
    Optional<Comment> findById(Long id);

    @Query("SELECT c FROM Comment c LEFT JOIN FETCH c.recomments WHERE c.post = :post AND c.parent IS NULL")
    List<Comment> findRootCommentsByPost(Post post);
}

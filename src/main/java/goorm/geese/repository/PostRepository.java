package goorm.geese.repository;

import goorm.geese.domain.entity.Device;
import goorm.geese.domain.entity.Member;
import goorm.geese.domain.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime to);

    Optional<List<Post>> findByAuthor(Member member);

    Optional<List<Post>> findByDevice(Device device);
}

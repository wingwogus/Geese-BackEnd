package goorm.geese.repository;

import goorm.geese.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}

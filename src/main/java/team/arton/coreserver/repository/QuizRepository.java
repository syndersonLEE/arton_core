package team.arton.coreserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.arton.coreserver.domain.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}

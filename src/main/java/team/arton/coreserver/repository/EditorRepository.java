package team.arton.coreserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.arton.coreserver.domain.Editor;

import java.util.Optional;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Long> {
    Optional<Editor> findById(Long id);
}

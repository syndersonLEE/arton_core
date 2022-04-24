package team.arton.coreserver.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.arton.coreserver.domain.Content;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.domain.WatchedContent;

import java.util.List;

@Repository
public interface WatchedContentRepository extends JpaRepository<WatchedContent, Long> {
    List<WatchedContent> findDistinctByUser(Pageable pageable, User user);
}

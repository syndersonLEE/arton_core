package team.arton.coreserver.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.arton.coreserver.domain.Bookmark;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findBookmarkByUserIdAndContentId(Long userId, Long contentId);
    List<Bookmark> findBookmarksByUserId(Pageable pageable, Long userId);

}

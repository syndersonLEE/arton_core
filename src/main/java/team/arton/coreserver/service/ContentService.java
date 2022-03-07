package team.arton.coreserver.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.arton.coreserver.common.auth.AuthContext;
import team.arton.coreserver.domain.Bookmark;
import team.arton.coreserver.exception.NotFoundException;
import team.arton.coreserver.model.ContentResDto;
import team.arton.coreserver.repository.BookmarkRepository;
import team.arton.coreserver.repository.ContentRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContentService {

    private ContentRepository contentRepository;
    private BookmarkRepository bookmarkRepository;

    public ContentService(ContentRepository contentRepository, BookmarkRepository bookmarkRepository) {
        this.contentRepository = contentRepository;
        this.bookmarkRepository = bookmarkRepository;
    }

    public List<ContentResDto> infiniteNewContentView(Long lastContentId, int pageSize, Long userId) {
        PageRequest pageRequest = PageRequest.of(0, pageSize, Sort.by("id").descending());
        List<ContentResDto> contentList;

        if(lastContentId == 0) {
            contentList = contentRepository.findAll(pageRequest).getContent().stream().map(ContentResDto::new).collect(Collectors.toList());
        } else {
            contentList = contentRepository.findByIdLessThan(pageRequest, lastContentId).stream().map(ContentResDto::new).collect(Collectors.toList());
        }

        // bookmark set 부분
        contentList.stream().map(content -> {
            boolean isBookmark = bookmarkRepository.findBookmarkByUserIdAndContentId(userId, content.getId()).isPresent();
            content.setBookmark(isBookmark);
            return content;
        }).collect(Collectors.toList());

        return contentList;
    }

    @Transactional
    public void enrollContentBookmark(Long userId, Long contentId) {
        Optional<Bookmark> bookmark = bookmarkRepository.findBookmarkByUserIdAndContentId(userId, contentId);
        if(bookmark.isEmpty()) bookmarkRepository.save(new Bookmark(userId, contentId));
    }

    @Transactional
    public void deleteContentBookmark(Long userId, Long contentId) throws NotFoundException {
        bookmarkRepository.findBookmarkByUserIdAndContentId(userId, contentId).ifPresent(bookmark -> bookmarkRepository.delete(bookmark));
    }

}

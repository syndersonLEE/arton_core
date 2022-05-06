package team.arton.coreserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.arton.coreserver.domain.*;
import team.arton.coreserver.exception.NotFoundException;
import team.arton.coreserver.model.reqdto.AdminContentReqDto;
import team.arton.coreserver.model.reqdto.ContentViewReqDto;
import team.arton.coreserver.model.resdto.ContentResDto;
import team.arton.coreserver.model.reqdto.QuizReqDto;
import team.arton.coreserver.repository.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContentService {

    private AuthRepository authRepository;
    private ContentRepository contentRepository;
    private BookmarkRepository bookmarkRepository;
    private EditorRepository editorRepository;
    private QuizRepository quizRepository;
    private WatchedContentRepository watchedContentRepository;

    public ContentService(AuthRepository authRepository, ContentRepository contentRepository, BookmarkRepository bookmarkRepository, EditorRepository editorRepository, QuizRepository quizRepository, WatchedContentRepository watchedContentRepository) {
        this.authRepository = authRepository;
        this.contentRepository = contentRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.editorRepository = editorRepository;
        this.quizRepository = quizRepository;
        this.watchedContentRepository = watchedContentRepository;
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
            boolean isBookmark = bookmarkRepository.findBookmarkByUserIdAndContentId(userId, content.getContentId()).isPresent();
            content.setBookmark(isBookmark);
            return content;
        }).collect(Collectors.toList());

        return contentList;
    }

    public List<ContentResDto>  infiniteRecentContentView(Long lastContentId, int pageSize, Long userId) {
        PageRequest pageRequest = PageRequest.of(0, pageSize, Sort.by("id").descending());
        List<ContentResDto> contentList;

        contentList = watchedContentRepository.findDistinctByUser(pageRequest, authRepository.findById(userId).orElseThrow()).stream().map(ContentResDto::new).collect(Collectors.toList());

        // bookmark set 부분
        contentList.stream().map(content -> {
            boolean isBookmark = bookmarkRepository.findBookmarkByUserIdAndContentId(userId, content.getContentId()).isPresent();
            content.setBookmark(isBookmark);
            return content;
        }).collect(Collectors.toList());

        return contentList;
    }

    public List<ContentResDto>  infiniteBookmarkContentView(Long lastContentId, int pageSize, Long userId) {
        PageRequest pageRequest = PageRequest.of(0, pageSize, Sort.by("id").descending());
        List<ContentResDto> contentList;

        contentList = bookmarkRepository.findBookmarksByUserId(pageRequest, userId).stream().map(bookmark -> {
            Content bookmarkOnContent = contentRepository.findById(bookmark.getContentId()).get();
            return new ContentResDto(bookmarkOnContent);
        }).collect(Collectors.toList());

        // bookmark set 부분
        contentList.stream().map(content -> {
            content.setBookmark(true);
            return content;
        }).collect(Collectors.toList());

        return contentList;
    }

    @Transactional
    public Content enrollAdminContent(AdminContentReqDto adminContentReqDto) {
        Editor editor = editorRepository.findById(adminContentReqDto.getAuthorId()).orElseThrow(() -> new NotFoundException("No Author"));

        QuizReqDto quizdto = adminContentReqDto.getQuiz();
        Quiz quiz = quizRepository.save(new Quiz(quizdto));

        Content content = Content.builder()
                .title(adminContentReqDto.getTitle())
                .editor(editor)
                .link(adminContentReqDto.getLink())
                .quiz(quiz)
                .thumbnailUrl(adminContentReqDto.getThumbnailUrl())
                .build();

        return contentRepository.save(content);
    }

    @Transactional
    public Content deleteAdminContent(ContentViewReqDto contentViewReqDto) {
        Content deletedContent = contentRepository.findById(contentViewReqDto.getContentId()).orElseThrow(() -> new NotFoundException("No Content"));
        contentRepository.deleteById(contentViewReqDto.getContentId());
        return deletedContent;
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

    @Transactional
    public void addContentViewCount(Long userId, Long contentId) throws NotFoundException {
        watchedContentRepository.save(new WatchedContent(authRepository.findById(userId).orElseThrow(), contentRepository.findById(contentId).orElseThrow()));
    }

}

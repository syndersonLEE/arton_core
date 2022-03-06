package team.arton.coreserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.arton.coreserver.model.ContentResDto;
import team.arton.coreserver.repository.ContentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContentService {

    private ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Transactional
    public List<ContentResDto> infiniteNewContentView(Long lastContentId, int pageSize) {
        PageRequest pageRequest = PageRequest.of(0, pageSize, Sort.by("id").descending());
        List<ContentResDto> contentList;

        if(lastContentId == 0) {
            contentList = contentRepository.findAll(pageRequest).getContent().stream().map(ContentResDto::new).collect(Collectors.toList());
        } else {
            contentList = contentRepository.findByIdLessThan(pageRequest, lastContentId).stream().map(ContentResDto::new).collect(Collectors.toList());
        }

        contentList.stream().map(content -> {
            content.setBookmark(true);
            return content;
        }).collect(Collectors.toList());

        return contentList;
    }


}

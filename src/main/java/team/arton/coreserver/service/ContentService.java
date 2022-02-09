package team.arton.coreserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.arton.coreserver.model.ContentResDto;
import team.arton.coreserver.repository.ContentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContentService {

    private ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public List<ContentResDto> getContentList() {
        return fetchContent(0, 3);
    }

    @Transactional
    public List<ContentResDto> fetchContent(int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize, Sort.by("id").descending());
        return contentRepository.findAll(pageRequest).stream().map(ContentResDto::new).toList();
    }

    @Transactional
    public List<ContentResDto> infiniteContentView(Long lastContentId, int pageSize) {
        PageRequest pageRequest = PageRequest.of(0, pageSize, Sort.by("id").descending());
        if(lastContentId == 0) return contentRepository.findAll(pageRequest).getContent().stream().map(ContentResDto::new).collect(Collectors.toList());
        return contentRepository.findByIdLessThan(pageRequest, lastContentId).stream().map(ContentResDto::new).collect(Collectors.toList());
    }
}

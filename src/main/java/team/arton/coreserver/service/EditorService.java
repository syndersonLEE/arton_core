package team.arton.coreserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.arton.coreserver.domain.Editor;
import team.arton.coreserver.model.resdto.ContentResDto;
import team.arton.coreserver.model.resdto.EditorResDto;
import team.arton.coreserver.repository.EditorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EditorService {

    private EditorRepository editorRepository;

    public EditorService(EditorRepository editorRepository) {
        this.editorRepository = editorRepository;
    }

    public List<EditorResDto> getEditorList() {
        return editorRepository.findAll().stream().map(EditorResDto::new).collect(Collectors.toList());
    }
}

package team.arton.coreserver.model.resdto;

import lombok.Getter;
import team.arton.coreserver.domain.Editor;

@Getter
public class EditorResDto {

    private Long editorId;

    private String editorName;

    private String editorThumbnail;

    private String editorDescription;


    public EditorResDto(Editor editor) {
        this.editorId = editor.getId();
        this.editorName = editor.getName();
        this.editorThumbnail = editor.getThumbnailUrl();
        this.editorDescription = editor.getDescription();
    }

}

package team.arton.coreserver.model;

import lombok.Getter;
import team.arton.coreserver.domain.Content;
import team.arton.coreserver.domain.WatchedContent;

@Getter
public class ContentResDto {

    private Long contentId;

    private String title;

    private String link;

    private String editor;

    private String thumbnailImage;

    private boolean bookmark;

    public ContentResDto(Content content) {
        this.contentId = content.getId();
        this.title = content.getTitle();
        this.link = content.getLink();
        this.editor = content.getEditor().getDescription();
        this.thumbnailImage = content.getThumbnailUrl();
    }

    public ContentResDto(WatchedContent watchedContent) {
        this.contentId = watchedContent.getContent().getId();
        this.title = watchedContent.getContent().getTitle();
        this.link = watchedContent.getContent().getLink();
        this.editor = watchedContent.getContent().getEditor().getDescription();
        this.thumbnailImage = watchedContent.getContent().getThumbnailUrl();
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }

}

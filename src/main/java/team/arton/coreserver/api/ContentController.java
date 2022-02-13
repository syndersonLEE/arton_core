package team.arton.coreserver.api;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.arton.coreserver.model.ContentResDto;
import team.arton.coreserver.model.DefaultResponse;
import team.arton.coreserver.model.StatusType;
import team.arton.coreserver.service.ContentService;

import java.util.List;

@RestController
public class ContentController {

    private ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    public DefaultResponse getNewContentList(@RequestParam(required = false, defaultValue = "0") Long lastContentId,
                                             @RequestParam(required = false, defaultValue = "6") int contentNum) {
        List<ContentResDto> contentResDtoList = contentService.infiniteNewContentView(lastContentId, contentNum);
        return DefaultResponse.res(StatusType.OK, contentResDtoList);
    }

    public DefaultResponse getWatchedContentList(@RequestParam(required = false, defaultValue = "0") Long lastContentId,
                                                 @RequestParam(required = false, defaultValue = "6") int contentNum) {
        return DefaultResponse.res(StatusType.OK);
    }
}

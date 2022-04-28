package team.arton.coreserver.api;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import team.arton.coreserver.common.auth.Auth;
import team.arton.coreserver.common.auth.AuthContext;
import team.arton.coreserver.model.*;
import team.arton.coreserver.model.resdto.ContentResDto;
import team.arton.coreserver.model.resdto.HomeReqDto;
import team.arton.coreserver.service.ContentService;

import java.util.List;

@RestController
public class ContentController {

    private ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @ApiOperation("New Content")
    @PostMapping("/api/v1/newcontent")
    @Auth
    public DefaultResponse getNewContentList(@RequestBody HomeReqDto homeReqDto) {
        Long userId = AuthContext.getUserId();
        List<ContentResDto> contentResDtoList = contentService.infiniteNewContentView(homeReqDto.getLastContentId(), homeReqDto.getContentNum(), userId);
        return DefaultResponse.res(StatusType.OK, new LastCheckModel(contentResDtoList, contentResDtoList.size() <= homeReqDto.getContentNum()));
    }

    @ApiOperation("Watched Content")
    @PostMapping("/api/v1/watchedcontent")
    @Auth
    public DefaultResponse getWatchedContentList(@RequestBody HomeReqDto homeReqDto) {
        Long userId = AuthContext.getUserId();
        List<ContentResDto> contentResDtoList = contentService.infiniteRecentContentView(homeReqDto.getLastContentId(), homeReqDto.getContentNum(), userId);
        return DefaultResponse.res(StatusType.OK, new LastCheckModel(contentResDtoList, contentResDtoList.size() <= homeReqDto.getContentNum()));
    }

    @ApiOperation("Add View")
    @PostMapping("/api/v1/viewcontent")
    @Auth
    public DefaultResponse postViewAdd(@RequestBody final ContentViewDto contentViewDto) {
        Long userId = AuthContext.getUserId();
        contentService.addContentViewCount(userId, contentViewDto.getContentId());

        return DefaultResponse.res(StatusType.OK);
    }
}

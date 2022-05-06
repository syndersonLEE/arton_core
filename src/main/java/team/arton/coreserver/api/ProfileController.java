package team.arton.coreserver.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.arton.coreserver.common.auth.Auth;
import team.arton.coreserver.common.auth.AuthContext;
import team.arton.coreserver.model.DefaultResponse;
import team.arton.coreserver.model.LastCheckModel;
import team.arton.coreserver.model.StatusType;
import team.arton.coreserver.model.resdto.ContentResDto;
import team.arton.coreserver.model.reqdto.HomeReqDto;
import team.arton.coreserver.service.ContentService;

import java.util.List;

@RestController
public class ProfileController {

    private ContentService contentService;

    public ProfileController(ContentService contentService) {
        this.contentService = contentService;
    }

    @ApiOperation("Bookmark Content")
    @PostMapping("/api/v1/bookmarkedcontent")
    @Auth
    public DefaultResponse getBookmarkContentList(@RequestBody HomeReqDto homeReqDto) {
        Long userId = AuthContext.getUserId();
        List<ContentResDto> contentResDtoList = contentService.infiniteBookmarkContentView(homeReqDto.getLastContentId(), homeReqDto.getContentNum(), userId);
        return DefaultResponse.res(StatusType.OK, new LastCheckModel(contentResDtoList, contentResDtoList.size() < homeReqDto.getContentNum()));
    }
}

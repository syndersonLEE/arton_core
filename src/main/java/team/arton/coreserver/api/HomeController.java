package team.arton.coreserver.api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team.arton.coreserver.common.auth.Auth;
import team.arton.coreserver.common.auth.AuthContext;
import team.arton.coreserver.domain.Bookmark;
import team.arton.coreserver.model.*;
import team.arton.coreserver.service.ContentService;

import java.util.List;

@RestController
@Slf4j
public class HomeController {
    private ContentService contentService;

    public HomeController(ContentService contentService) {
        this.contentService = contentService;
    }

    @ApiOperation("홈 화면 조회")
    @GetMapping("/api/v1/home")
    @Auth
    public DefaultResponse getHome(@RequestBody final HomeReqDto homeReqDto) {
        Long userId = AuthContext.getUserId();
        log.info("{} - getContentNum",  homeReqDto.getContentNum());
        List<ContentResDto> contentResDtoList = contentService.infiniteNewContentView(homeReqDto.getLastContentId(), homeReqDto.getContentNum(), userId);
        return DefaultResponse.res(StatusType.OK, contentResDtoList);
    }

    @ApiOperation("북마크 켜기")
    @PostMapping("/api/v1/bookmark")
    @Auth
    public DefaultResponse enrollBookmark(@RequestBody final BookmarkReq bookmarkReq) {
        Long userId = AuthContext.getUserId();
        contentService.enrollContentBookmark(userId, bookmarkReq.getContentId());
        return DefaultResponse.res(StatusType.CREATED, bookmarkReq);
    }

    @ApiOperation("북마크 끄기")
    @DeleteMapping("/api/v1/bookmark")
    @Auth
    public DefaultResponse deleteBookmark(@RequestBody final BookmarkReq bookmarkReq) {
        Long userId = AuthContext.getUserId();
        contentService.deleteContentBookmark(userId, bookmarkReq.getContentId());
        return DefaultResponse.res(StatusType.OK, bookmarkReq);
    }


}


































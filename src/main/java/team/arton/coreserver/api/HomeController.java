package team.arton.coreserver.api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team.arton.coreserver.common.auth.Auth;
import team.arton.coreserver.common.auth.AuthContext;
import team.arton.coreserver.model.*;
import team.arton.coreserver.model.reqdto.BookmarkReqDto;
import team.arton.coreserver.model.resdto.ContentResDto;
import team.arton.coreserver.model.reqdto.HomeReqDto;
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
    @PostMapping("/api/v1/home")
    @Auth
    public DefaultResponse getHome(@RequestBody final HomeReqDto homeReqDto) {
        Long userId = AuthContext.getUserId();
        List<ContentResDto> contentResDtoList = contentService.infiniteNewContentView(homeReqDto.getLastContentId(), homeReqDto.getContentNum(), userId);
        log.info("{} - contentResDtoList.size(), {} - homeReqDto.getContentNum()", contentResDtoList.size(), homeReqDto.getContentNum());
        return DefaultResponse.res(StatusType.OK, new LastCheckModel(contentResDtoList, contentResDtoList.size() < homeReqDto.getContentNum()));
    }

    @ApiOperation("북마크 켜기")
    @PostMapping("/api/v1/bookmark")
    @Auth
    public DefaultResponse enrollBookmark(@RequestBody final BookmarkReqDto bookmarkReqDto) {
        Long userId = AuthContext.getUserId();
        contentService.enrollContentBookmark(userId, bookmarkReqDto.getContentId());
        return DefaultResponse.res(StatusType.CREATED, bookmarkReqDto);
    }

    @ApiOperation("북마크 끄기")
    @DeleteMapping("/api/v1/bookmark")
    @Auth
    public DefaultResponse deleteBookmark(@RequestBody final BookmarkReqDto bookmarkReqDto) {
        Long userId = AuthContext.getUserId();
        contentService.deleteContentBookmark(userId, bookmarkReqDto.getContentId());
        return DefaultResponse.res(StatusType.OK, bookmarkReqDto);
    }

}


































package team.arton.coreserver.api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team.arton.coreserver.common.auth.Auth;
import team.arton.coreserver.common.auth.AuthContext;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.exception.UnAuthorizedException;
import team.arton.coreserver.model.AdminContentReqDto;
import team.arton.coreserver.model.DefaultResponse;
import team.arton.coreserver.model.StatusType;
import team.arton.coreserver.service.ContentService;

@RestController
@Slf4j
public class AdminController {

    private ContentService contentService;

    public AdminController(ContentService contentService) {
        this.contentService = contentService;
    }

    @ApiOperation("Content 등록")
    @PostMapping("/api/admin/content")
    @Auth
    public DefaultResponse enrollContent(@RequestBody AdminContentReqDto adminContentReqDto) {
        User user = AuthContext.getUser().get();
        if(!user.getType().toLowerCase().equals("admin")) {
            throw new UnAuthorizedException("비인가 접근입니다.");
        }
        return DefaultResponse.res(StatusType.OK, contentService.enrollAdminContent(adminContentReqDto));
    }





}

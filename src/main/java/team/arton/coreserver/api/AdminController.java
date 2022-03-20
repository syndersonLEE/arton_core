package team.arton.coreserver.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.arton.coreserver.common.auth.Auth;
import team.arton.coreserver.common.auth.AuthContext;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.exception.UnAuthorizedException;
import team.arton.coreserver.model.DefaultResponse;
import team.arton.coreserver.model.StatusType;
import team.arton.coreserver.service.ContentService;

@RestController
public class AdminController {

    private ContentService contentService;

    public AdminController(ContentService contentService) {
        this.contentService = contentService;
    }

    @ApiOperation("Content 등록")
    @PostMapping("/api/admin/content")
    @Auth
    public DefaultResponse enrollContent() {
        User user = AuthContext.getUser().get();

        if(user.getType() != "ADMIN")
            throw new UnAuthorizedException("비인가 접근입니다.");

        return DefaultResponse.res(StatusType.OK);
    }


}

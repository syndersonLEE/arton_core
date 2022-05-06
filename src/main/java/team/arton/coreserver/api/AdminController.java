package team.arton.coreserver.api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team.arton.coreserver.common.auth.Auth;
import team.arton.coreserver.common.auth.AuthContext;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.exception.UnAuthorizedException;
import team.arton.coreserver.model.reqdto.AdminContentReqDto;
import team.arton.coreserver.model.DefaultResponse;
import team.arton.coreserver.model.StatusType;
import team.arton.coreserver.model.reqdto.ContentViewReqDto;
import team.arton.coreserver.service.ContentService;
import team.arton.coreserver.service.EditorService;

@RestController
@Slf4j
public class AdminController {

    private ContentService contentService;
    private EditorService editorService;

    public AdminController(ContentService contentService, EditorService editorService) {
        this.contentService = contentService;
        this.editorService = editorService;
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

    @ApiOperation("Content 삭제")
    @DeleteMapping("/api/admin/content")
    @Auth
    public DefaultResponse deleteContent(@RequestBody ContentViewReqDto contentViewReqDto) {
        User user = AuthContext.getUser().get();
        if(!user.getType().toLowerCase().equals("admin")) {
            throw new UnAuthorizedException("비인가 접근입니다.");
        }
        return DefaultResponse.res(StatusType.OK, contentService.deleteAdminContent(contentViewReqDto));
    }

    @ApiOperation("Editor 조회")
    @GetMapping("/api/admin/editorlist")
    @Auth
    public DefaultResponse getEditorList() {
        User user = AuthContext.getUser().get();
        if(!user.getType().toLowerCase().equals("admin")) {
            throw new UnAuthorizedException("비인가 접근입니다.");
        }
        return DefaultResponse.res(StatusType.OK, editorService.getEditorList());
    }




}

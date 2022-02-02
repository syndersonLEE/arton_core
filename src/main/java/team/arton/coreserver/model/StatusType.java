package team.arton.coreserver.model;

import lombok.Getter;

@Getter
public enum StatusType {
    OK(200, "OK"),
    CREATED(201, "생성되었습니다"),
    NOCONTENT(204, "없는 계정입니다"),
    BADREQUEST(400, "잘못된 요청입니다"),
    UNAUTHORIZED(401, "승인되지 않은 계정입니다"),
    NOTFOUND(404, "해당 정보는 존재 하지 않습니다"),
    CONFLICT(409, "이미 있는 아이디 입니다"),
    INTERNALERROR(500, "잠시 후 시도 바랍니다"),
    SERVICE_UNAVAILABLE(503, "현재 개발 중인 서비스 입니다");

    private final int code;
    private final String message;

    StatusType(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

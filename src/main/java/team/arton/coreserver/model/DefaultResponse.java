package team.arton.coreserver.model;

import lombok.Getter;

@Getter
public class DefaultResponse {
    private int status;
    private String message;
    private Object data;

    public DefaultResponse(final int status, final String message, final Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public DefaultResponse(final int status, final String message) {
        this(status, message, null);
    }

    public DefaultResponse(final Object data) {
        this.data = data;
    }

    public static DefaultResponse res(final StatusType statusType, final Object data) {
        return new DefaultResponse(statusType.getCode(), statusType.getMessage(), data);
    }

    public static DefaultResponse res(final StatusType statusType) {
        return new DefaultResponse(statusType.getCode(), statusType.getMessage(), null);
    }
}

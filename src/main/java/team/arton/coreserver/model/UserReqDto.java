package team.arton.coreserver.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserReqDto {
    @NotNull
    private SocialType type;

    @NotNull
    private String userId;

    private String nickname;

    public String typeName() {
        return type.name().toLowerCase();
    }
}

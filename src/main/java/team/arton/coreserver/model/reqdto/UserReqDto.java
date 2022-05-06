package team.arton.coreserver.model.reqdto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.ToString;
import team.arton.coreserver.model.SocialType;

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

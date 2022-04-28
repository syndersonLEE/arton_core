package team.arton.coreserver.model.resdto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.model.SocialType;
import team.arton.coreserver.service.JwtService;

@Getter
@AllArgsConstructor
public class UserResDto {

    @NotNull
    private SocialType type;

    @NotNull
    private String accessToken;

    @NotNull
    private String nickname;

    @NotNull
    private boolean isNewUser;

    public UserResDto(User user, String token, boolean isNewUser) {
        this.type = SocialType.valueOf(user.getType().toUpperCase());
        this.accessToken = token;
        this.nickname = user.getNickname();
        this.isNewUser = isNewUser;
    }
}

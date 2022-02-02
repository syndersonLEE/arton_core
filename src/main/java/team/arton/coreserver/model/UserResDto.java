package team.arton.coreserver.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team.arton.coreserver.domain.User;
import utils.JwtParser;

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

    public UserResDto(User user, boolean isNewUser) {
        this.type = SocialType.valueOf(user.getType().toUpperCase());
        this.accessToken = JwtParser.createToken(user.getId());
        this.nickname = user.getNickname();
        this.isNewUser = isNewUser;
    }

}

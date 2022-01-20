package team.arton.coreserver.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class LoginResponseDto {
    @NotNull
    private SocialType type;

    @NotNull
    private String accessToken;

    public LoginResponseDto(SocialType type, String accessToken) {
        this.type = type;
        this.accessToken = accessToken;
    }
}

package team.arton.coreserver.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.arton.coreserver.exception.SocialLoginTypeException;
import team.arton.coreserver.model.LoginResponseDto;
import team.arton.coreserver.model.SocialType;

@RequiredArgsConstructor
@Component
public class GoogleVerifier implements UserSocialLoginVerifier{

    @Override
    public LoginResponseDto apiRequest(String accessToken) {
        try {
            return new LoginResponseDto(SocialType.GOOGLE, "test");
        } catch (Exception e) {
            throw new SocialLoginTypeException();
        }
    }

}


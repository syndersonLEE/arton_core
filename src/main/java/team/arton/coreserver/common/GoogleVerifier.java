package team.arton.coreserver.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.arton.coreserver.exception.SocialLoginTypeException;
import team.arton.coreserver.model.UserResDto;
import team.arton.coreserver.model.SocialType;

@RequiredArgsConstructor
@Component
public class GoogleVerifier implements UserSocialLoginVerifier{

    @Override
    public UserResDto apiRequest(String accessToken) {
        try {
            return new UserResDto(SocialType.GOOGLE, "test", "", true);
        } catch (Exception e) {
            throw new SocialLoginTypeException();
        }
    }

}


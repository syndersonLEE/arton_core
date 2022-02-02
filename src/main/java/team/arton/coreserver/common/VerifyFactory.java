package team.arton.coreserver.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.arton.coreserver.exception.SocialLoginTypeException;

@RequiredArgsConstructor
@Component
public class VerifyFactory {

    private final GoogleVerifier googleVerifier;

//    public UserSocialLoginVerifier getVerifier(String userType) {
//        return switch (userType.toUpperCase()) {
//            case "GOOGLE" -> googleVerifier;
//            default -> throw new SocialLoginTypeException();
//        };
//    }
}

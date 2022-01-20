package team.arton.coreserver.common;

import team.arton.coreserver.model.LoginDto;
import team.arton.coreserver.model.LoginResponseDto;

public interface UserSocialLoginVerifier {
    LoginResponseDto apiRequest(String authToken);
}

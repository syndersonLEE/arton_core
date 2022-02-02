package team.arton.coreserver.common;

import team.arton.coreserver.model.UserResDto;

public interface UserSocialLoginVerifier {
    UserResDto apiRequest(String authToken);
}

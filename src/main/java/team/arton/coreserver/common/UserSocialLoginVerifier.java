package team.arton.coreserver.common;

import team.arton.coreserver.model.resdto.UserResDto;

public interface UserSocialLoginVerifier {
    UserResDto apiRequest(String authToken);
}

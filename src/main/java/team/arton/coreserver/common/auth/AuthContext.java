package team.arton.coreserver.common.auth;

import team.arton.coreserver.domain.User;

import java.util.Optional;

public class AuthContext {
    public static final ThreadLocal<User> userContext = new ThreadLocal<>();

    public static Optional<User> getUser() {
        return Optional.ofNullable(AuthContext.userContext.get());
    }

    public static Long getUserId() {
        if(AuthContext.userContext.get() != null) {
            return AuthContext.userContext.get().getId();
        } else {
            throw new RuntimeException();
        }
    }
}

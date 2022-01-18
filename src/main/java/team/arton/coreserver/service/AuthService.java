package team.arton.coreserver.service;

import org.springframework.stereotype.Service;
import team.arton.coreserver.repository.AuthRepository;

@Service
public class AuthService {
    private AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public String testMethod() {
        return authRepository.findById(1L).get().getEmail();
    }

}

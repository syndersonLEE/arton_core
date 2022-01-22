package team.arton.coreserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.arton.coreserver.domain.User;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByTypeAndServerid(String type, String serverid);
    Optional<User> findUserByNickname(String nickname);
}
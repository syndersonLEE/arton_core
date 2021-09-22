package team.arton.coreserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.arton.coreserver.domain.User;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

}
package at.fischelmayer.securitydemo.repositories;

import at.fischelmayer.securitydemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
}

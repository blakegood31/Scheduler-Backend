package superfrog_scheduler.backend.scheduleruser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    // Derived query method example
    /*Optional<User> findByUsernameAndPassword(String username, String password);*/

}

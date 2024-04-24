package superfrog_scheduler.backend.director;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, String> {
    Optional<Director> findByEmail(String email);
}

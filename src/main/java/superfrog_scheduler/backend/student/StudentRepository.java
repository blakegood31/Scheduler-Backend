package superfrog_scheduler.backend.student;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findAll(Specification<Student> searchSpecification);

    /*@Query("SELECT s FROM Student s")
    List<Student> getAllStudent();*/

    Student findStudentByEmail(String email);
}

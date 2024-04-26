package superfrog_scheduler.backend.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

    /*@Query("SELECT s FROM Student s")
    List<Student> getAllStudent();*/

    //Student findStudentByEmail(String email);
}

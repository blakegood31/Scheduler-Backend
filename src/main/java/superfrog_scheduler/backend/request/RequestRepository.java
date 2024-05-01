package superfrog_scheduler.backend.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import superfrog_scheduler.backend.student.Student;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {
    List<Request> findByIdIn(List<String> idList);

    List<Request> findByStatusAndStudent(RequestStatus status, Student student);
    //List<Request> findByStatus(RequestStatus status);
    /*List<Request> findBySuperfrog(Student student);
    List<Request> findByStatusAndSuperfrogIsNull(RequestStatus requestStatus);*/
}

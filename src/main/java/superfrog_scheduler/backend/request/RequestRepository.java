package superfrog_scheduler.backend.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import superfrog_scheduler.backend.student.Student;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {
    List<Request> findByIdIn(List<String> idList);

    @Query(value = "SELECT * FROM request WHERE status = :status and sid = :sid", nativeQuery = true)
    List<Request> findByStatusAndStudent(@Param("status") RequestStatus status, @Param("sid") String sid);
    //List<Request> findByStatus(RequestStatus status);
    /*List<Request> findBySuperfrog(Student student);
    List<Request> findByStatusAndSuperfrogIsNull(RequestStatus requestStatus);*/
}

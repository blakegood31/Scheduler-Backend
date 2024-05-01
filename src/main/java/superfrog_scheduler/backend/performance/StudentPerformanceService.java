package superfrog_scheduler.backend.performance;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.request.Request;
import superfrog_scheduler.backend.request.RequestService;
import superfrog_scheduler.backend.request.RequestStatus;
import superfrog_scheduler.backend.student.Student;

import java.util.List;

@Service
@Transactional
public class StudentPerformanceService {
    private final RequestService requestService;

    public StudentPerformanceService(RequestService requestService) {
        this.requestService = requestService;
    }

    public int getCompletedRequests(RequestStatus status, Student student) {
        List<Request> completedCount = requestService.findByStatusAndStudent(status, student);
        return completedCount.size();
    }
}

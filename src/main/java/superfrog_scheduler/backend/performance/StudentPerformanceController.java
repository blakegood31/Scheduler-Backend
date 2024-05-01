package superfrog_scheduler.backend.performance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import superfrog_scheduler.backend.request.RequestStatus;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.StudentService;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;

@RestController
@RequestMapping("/performance")
public class StudentPerformanceController {
    private final StudentPerformanceService studentPerformanceService;

    private final StudentService studentService;

    public StudentPerformanceController(StudentPerformanceService studentPerformanceService, StudentService studentService) {
        this.studentPerformanceService = studentPerformanceService;
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public Result getSuperfrogPerformance(@PathVariable String studentId) {
        Student student = studentService.findById(studentId);
        int completedRequests = studentPerformanceService.getCompletedRequests(RequestStatus.COMPLETED, student);
        return new Result(true, StatusCode.SUCCESS, "Completed Request Success", completedRequests);

    }
}

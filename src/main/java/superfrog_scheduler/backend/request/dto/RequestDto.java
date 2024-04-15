package superfrog_scheduler.backend.request.dto;

import superfrog_scheduler.backend.customer.Customer;
import superfrog_scheduler.backend.request.RequestStatus;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.dto.StudentDto;
import java.time.LocalDateTime;


public record RequestDto(String id,
                         String address,
                         String description,
                         LocalDateTime startTime,
                         LocalDateTime endTime,
                         String eventTitle,
                         RequestStatus status,
                         Student superfrog,
                         Customer customer,
                         String specialInstructions,
                         String other_orgs) {

}

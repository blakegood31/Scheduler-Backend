package superfrog_scheduler.backend.student.dto;

import superfrog_scheduler.backend.request.Request;

import java.util.List;

public record StudentDto(String id,
                         String firstName,
                         String lastName,
                         float performanceRating,
                         String phoneNumber,
                         String email,
                         Boolean active){
}

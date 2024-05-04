package superfrog_scheduler.backend.student.converter;

import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.dto.StudentDto;

@Component
public class StudentToStudentDtoConverter {

    public StudentDto convert(Student student) {
        StudentDto studentDto = new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPerformance_rating(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.isActive()
        );
        // Map other properties as needed
        return studentDto;
    }
}

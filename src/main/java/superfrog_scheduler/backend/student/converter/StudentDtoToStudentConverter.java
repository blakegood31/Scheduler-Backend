package superfrog_scheduler.backend.student.converter;

import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.dto.StudentDto;

@Component
public class StudentDtoToStudentConverter {

    public Student convert(StudentDto studentDto) {
        Student student = new Student();
        // Map properties from StudentDto to Student
        student.setId(studentDto.id());
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setPerformance_rating(studentDto.performanceRating());
        student.setPhoneNumber(studentDto.phoneNumber());
        student.setEmail(studentDto.email());

        // Map other properties as needed
        return student;
    }
}

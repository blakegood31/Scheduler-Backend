package superfrog_scheduler.backend.student.converter;

import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.dto.StudentDto;

@Component
public class StudentToStudentDtoConverter {

    public StudentDto convert(Student student) {
        StudentDto studentDto = new StudentDto();
        // Map properties from Student to StudentDto
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        // Map other properties as needed
        return studentDto;
    }
}

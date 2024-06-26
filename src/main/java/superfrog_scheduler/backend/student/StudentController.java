
package superfrog_scheduler.backend.student;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import superfrog_scheduler.backend.request.Request;
import superfrog_scheduler.backend.student.converter.AssignedEventsToAssignedEventsDtoConverter;
import superfrog_scheduler.backend.student.converter.StudentDtoToStudentConverter;
import superfrog_scheduler.backend.student.converter.StudentToStudentDtoConverter;
import superfrog_scheduler.backend.student.dto.AssignedEventsDto;
import superfrog_scheduler.backend.student.dto.StudentDto;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentToStudentDtoConverter studentToStudentDtoConverter;
    private final StudentDtoToStudentConverter studentDtoToStudentConverter;

    private final AssignedEventsToAssignedEventsDtoConverter assignedEventsToAssignedEventsDtoConverter;

    public StudentController(StudentService studentService, StudentToStudentDtoConverter studentToStudentDtoConverter, StudentDtoToStudentConverter studentDtoToStudentConverter, AssignedEventsToAssignedEventsDtoConverter assignedEventsToAssignedEventsDtoConverter) {
        this.studentService = studentService;
        this.studentToStudentDtoConverter = studentToStudentDtoConverter;
        this.studentDtoToStudentConverter = studentDtoToStudentConverter;
        this.assignedEventsToAssignedEventsDtoConverter = assignedEventsToAssignedEventsDtoConverter;
    }

    @GetMapping
    public Result findAllStudents() {
        List<Student> studentDtos = this.studentService.findAll();
        studentDtos.stream()
                .map(this.studentToStudentDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all success", studentDtos);
    }

    @GetMapping("/{studentid}")
    public Result findStudentById(@PathVariable String studentid) {
        Student foundStudent = this.studentService.findById(studentid);
        return new Result(true, StatusCode.SUCCESS, "Find One Success");
    }

    @PostMapping
    public Result addStudent(@RequestBody StudentDto studentDto) {
        // Convert artifactDto to artifact
        Student newStudent = this.studentDtoToStudentConverter.convert(studentDto);
        Student savedStudent = this.studentService.save(newStudent);
        StudentDto savedStudentDto = this.studentToStudentDtoConverter.convert(savedStudent);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedStudentDto);
    }

    @PutMapping("/{studentId}/availability/{calendarId}")
    public Result assignAvailability(@PathVariable String studentId, @PathVariable String calendarId) {
        this.studentService.assignAvailability(studentId, calendarId);
        return new Result(true, StatusCode.SUCCESS, "Availability Assignment Success");
    }

    @PutMapping("/{studentId}/profile")
    public Result editProfile(@PathVariable String studentId, @RequestBody StudentDto updatedProfile) {
        Student student = studentService.findById(studentId);
        boolean isValid = validateProfile(updatedProfile);
        if (!isValid) {
            return new Result(false, StatusCode.INVALID_ARGUMENT, "Invalid profile information");
        }
        updateProfile(student, updatedProfile);
        studentService.save(student);
        notifyProfileModification(student);
        return new Result(true, StatusCode.SUCCESS, "Profile updated successfully");
    }

    @GetMapping("/{sid}/assigned")
    public Result getAssignedEvent(@PathVariable String sid){
        Student student = this.studentService.findById(sid);
        List<Request> studentRequests = student.getAssignedEvents();
        AssignedEventsDto assignedEventsDto = this.assignedEventsToAssignedEventsDtoConverter.convert(studentRequests);
        return new Result(true, StatusCode.SUCCESS, "Find assigned events success", assignedEventsDto);

    }

    private boolean validateProfile(StudentDto updatedStudentDto) {
        // Implement validation logic according to the business rules
        // For example:
        Student updatedProfile = this.studentDtoToStudentConverter.convert(updatedStudentDto);
        if (updatedProfile.getPhoneNumber() != null && !isValidPhoneNumber(updatedProfile.getPhoneNumber())) {
            return false;
        }
        // Add more validation rules as needed
        return true;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Implement phone number validation logic (e.g., using regex)
        // Return t
        // rue if valid, false otherwise
        return true;
    }

    private void updateProfile(Student student, StudentDto updatedStudentDto) {
        Student updatedProfile = this.studentDtoToStudentConverter.convert(updatedStudentDto);
        // Update student entity with the information from the updated profile
        if (updatedProfile.getFirstName() != null) {
            student.setFirstName(updatedProfile.getFirstName());
        }
        if (updatedProfile.getLastName() != null) {
            student.setLastName(updatedProfile.getLastName());
        }
        if (updatedProfile.getPhoneNumber() != null) {
            student.setPhoneNumber(updatedProfile.getPhoneNumber());
        }
        // Add more update logic for other profile properties
    }

    private void notifyProfileModification(Student student) {
        // Implement notification logic to notify relevant actors about the profile modification
        // For example, send an email notification to the Spirit Director
    }

    //UC15-find student frogs by name(first and/or last), email, or phone
    @PostMapping("/searchFrogs")
    public Result findSuperFrogStudent(@Valid @RequestBody StudentDto studentDto){

        Student student = this.studentDtoToStudentConverter.convert(studentDto);

        List<Student> students = this.studentService
                .findSuperFrogStudent(student.getFirstName(), student.getLastName(), student.getPhoneNumber(), student.getEmail());

        List<StudentDto> studentDtos = students
                .stream()
                .map(this.studentToStudentDtoConverter::convert)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "Find Student Success", studentDtos);
    }
}

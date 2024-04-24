package superfrog_scheduler.backend.student;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import superfrog_scheduler.backend.system.exceptions.ObjectNotFoundException;
import superfrog_scheduler.backend.utils.IdWorker;

import java.util.List;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final IdWorker idWorker;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.idWorker = new IdWorker();
    }

    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    public Student findById(String studentId) {
        return this.studentRepository.findById(studentId)
                .orElseThrow(() -> new ObjectNotFoundException("student", studentId));
    }

    public Student save(Student student) {
        student.setId(idWorker.nextId() + "");
        return this.studentRepository.save(student);
    }

    public Student updateProfileInformation(String studentId, String firstName, String lastName, String phoneNumber, String email) {
        Student student = findById(studentId);
        // Update the student's profile information
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setPhoneNumber(phoneNumber);
        student.setEmail(email);
        // Save and return the updated student
        return save(student);
    }

    public void assignAvailability(String studentId, String calendarId) {
        // Implement this method if needed
    }
}
package superfrog_scheduler.backend.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.superfrog_calendar.SuperfrogCalendar;
import superfrog_scheduler.backend.superfrog_calendar.SuperfrogCalendarRepository;
import superfrog_scheduler.backend.system.exceptions.ObjectNotFoundException;
import superfrog_scheduler.backend.utils.IdWorker;

import java.util.List;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;

    private final SuperfrogCalendarRepository superfrogCalendarRepository;
    private final IdWorker idWorker;
    public StudentService(StudentRepository studentRepository, SuperfrogCalendarRepository superfrogCalendarRepository) {
        this.studentRepository = studentRepository;
        this.idWorker = new IdWorker();
        this.superfrogCalendarRepository = superfrogCalendarRepository;
    }

    public List<Student> findAll(){
        return this.studentRepository.findAll();
    }

    public Student findById(String studentid){

        return this.studentRepository.findById(studentid)
                .orElseThrow(() -> new ObjectNotFoundException("superfrog", studentid));
    }

    public Student save(Student newStudent){
        newStudent.setId(idWorker.nextId() + "");
        return this.studentRepository.save(newStudent);
    }

    public void assignAvailability(String studentId, String calendarId){
        SuperfrogCalendar sfc = this.superfrogCalendarRepository.findById(calendarId)
                .orElseThrow(() -> new ObjectNotFoundException("calendar_entry", calendarId));

        Student superfrog = this.studentRepository.findById(studentId)
                .orElseThrow(() -> new ObjectNotFoundException("superfrog", studentId));

        superfrog.addAvailability(sfc);
    }

}

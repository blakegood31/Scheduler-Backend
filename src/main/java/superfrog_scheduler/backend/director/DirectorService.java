package superfrog_scheduler.backend.director;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.StudentRepository;
import superfrog_scheduler.backend.student.utils.SuperFrogStudentSpecifications;
import superfrog_scheduler.backend.system.exceptions.ObjectNotFoundException;

import java.util.List;

@Service
@Transactional
public class DirectorService {
    private final DirectorRepository directorRepository;

    private final StudentRepository studentRepository;

    /*private final PasswordEncoder passwordEncoder;*/
    private final SuperFrogStudentSpecifications superFrogStudentSpecifications;

    public DirectorService(DirectorRepository directorRepository, StudentRepository studentRepository, SuperFrogStudentSpecifications superFrogStudentSpecifications) {
        this.directorRepository = directorRepository;
        this.studentRepository = studentRepository;
        /*this.passwordEncoder = passwordEncoder;*/
        this.superFrogStudentSpecifications = superFrogStudentSpecifications;
    }

    public List<Director> findAll() {
        return this.directorRepository.findAll();
    }

    //UC16
    public Director findById(String id) {
        return this.directorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("user", id));
    }

    public List<Student> findSuperFrogStudent(String firstName, String lastName, String phoneNumber, String email){
        Specification<Student> searchSpecification = superFrogStudentSpecifications
                .superFrogStudentFilters(firstName, lastName, phoneNumber, email);

        return this.studentRepository.findAll(searchSpecification);
    }

    /*public Director disableUser(String email) throws UsernameNotFoundException {
        Director userToBeDisabled = this.directorRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("username " + email + " is not found."));
        userToBeDisabled.setActive(false);
        this.directorRepository.save(userToBeDisabled);
        UserDetails userToBeDisabledDetails = new MyUserPrincipal(userToBeDisabled);
        Student studentToBeDisabled = this.studentRepository.findStudentByEmail(email);
        studentToBeDisabled.setActive(false);
        this.studentRepository.save(studentToBeDisabled);
        return userToBeDisabled;
    }*/

    /*public Director enableUser(String email) throws UsernameNotFoundException {
        Director userToBeEnabled = this.directorRepository.findByEmail(email).orElseThrow();
        userToBeEnabled.setActive(true);
        UserDetails userToBeEnabledDetails = new MyUserPrincipal(userToBeEnabled);
        this.directorRepository.save(userToBeEnabled);
        Student studentToBeEnabled = this.studentRepository.findStudentByEmail(email);
        studentToBeEnabled.setActive(true);
        this.studentRepository.save(studentToBeEnabled);
        return userToBeEnabled;
    }

    public Director save(Director newUser) {
        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        return this.directorRepository.save(newUser);
    }*/

    /*public Director update(String id, Director update) {
        Director oldUser = this.directorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("user", id));
        oldUser.setActive(update.isActive());
        oldUser.setRoles(update.getRoles());
        return this.directorRepository.save(oldUser);
    }

    //UC14
    public void delete(String id) {
        this.directorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("user", id));
        this.directorRepository.deleteById(id);
    }*/

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.directorRepository.findByEmail(email)
                .map(Director -> new MyUserPrincipal(Director))
                .orElseThrow(() -> new UsernameNotFoundException("username " + email + " is not found."));

    }*/
}

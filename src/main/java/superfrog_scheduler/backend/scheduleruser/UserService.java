package superfrog_scheduler.backend.scheduleruser;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.StudentRepository;
import superfrog_scheduler.backend.system.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final StudentRepository studentRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Integer userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId.toString()));
    }

    public User save(User newUser) {
        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        return this.userRepository.save(newUser);
    }

    /**
     * We are not using this update to change user password.
     *
     * @param userId
     * @param update
     * @return
     */
    public User update(Integer userId, User update) {
        User oldUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId.toString()));
        oldUser.setUsername(update.getUsername());
        oldUser.setEnabled(update.isEnabled());
        oldUser.setRoles(update.getRoles());
        return this.userRepository.save(oldUser);
    }

    public void delete(Integer userId) {
        this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId.toString()));
        this.userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(user -> {
                    // Check if user password is currently stored as Bcrypt password
                    // Needed when adding users directly through SQL w/ "raw" password
                    if(!isBcryptPassword(user.getPassword())){
                        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
                        this.userRepository.save(user);
                    }
                    return new MyUserPrincipal(user);
                })
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " is not found."));
    }

    private boolean isBcryptPassword(String password){
        Pattern bcryptPattern = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");
        return bcryptPattern.matcher(password).matches();
    }

    //UC14
    public User disableUser(String username) throws UsernameNotFoundException {
        User userToBeDisabled = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username " + username + " is not found."));
        userToBeDisabled.setEnabled(false);
        this.userRepository.save(userToBeDisabled);
        UserDetails userToBeDisabledDetails = new MyUserPrincipal(userToBeDisabled);
        Student superFrogStudentToBeDisabled = this.studentRepository.findStudentByEmail(username);
        superFrogStudentToBeDisabled.setActive(false);
        this.studentRepository.save(superFrogStudentToBeDisabled);
        return userToBeDisabled;
    }

    public User enableUser(String username) throws UsernameNotFoundException {
        User userToBeEnabled = this.userRepository.findByUsername(username).orElseThrow();
        userToBeEnabled.setEnabled(true);
        UserDetails userToBeEnabledDetails = new MyUserPrincipal(userToBeEnabled);
        this.userRepository.save(userToBeEnabled);
        Student superFrogStudentToBeEnabled = this.studentRepository.findStudentByEmail(username);
        superFrogStudentToBeEnabled.setActive(true);
        this.studentRepository.save(superFrogStudentToBeEnabled);
        return userToBeEnabled;
    }
}

package superfrog_scheduler.backend.scheduleruser;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import superfrog_scheduler.backend.director.Director;
import superfrog_scheduler.backend.student.Student;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username")
    @NotEmpty(message="Username is required.")
    private String username;

    @Column(name="password")
    @NotEmpty(message="Password is required.")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name="roles")
    @NotEmpty(message = "Roles are required.")
    private String roles;

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}

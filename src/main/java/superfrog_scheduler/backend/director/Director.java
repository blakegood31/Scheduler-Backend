package superfrog_scheduler.backend.director;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

@Entity
public class Director implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;


    @NotEmpty(message = "Email is required.")
    private String email;


    @NotEmpty(message = "Password is required.")
    private String password;

    private boolean active;

    @NotEmpty(message = "Roles are required.")
    private String roles;

    public Director() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}

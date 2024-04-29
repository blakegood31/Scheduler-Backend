package superfrog_scheduler.backend.student.dto;

import superfrog_scheduler.backend.request.Request;

import java.util.List;

public record StudentDto(String id, String firstName, String lastName, float performanceRating, String phoneNumber, String email){
}
/*public class StudentDto {
    private String id;
    private String name;
    private float performanceRating;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;*/

// Constructors
   /* public StudentDto() {
    }

    public StudentDto(String id, String name, float performanceRating, String firstName, String lastName, String phoneNumber, String email, boolean isInternationalStudent, String paymentPreference) {
        this.id = id;
        this.name = name;
        this.performanceRating = performanceRating;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(float performanceRating) {
        this.performanceRating = performanceRating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}*/
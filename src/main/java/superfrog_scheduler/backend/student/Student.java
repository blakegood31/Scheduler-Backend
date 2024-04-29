package superfrog_scheduler.backend.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import superfrog_scheduler.backend.request.Request;
import superfrog_scheduler.backend.superfrog_calendar.SuperfrogCalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "performance")
    private float performance_rating;

    // New profile information properties

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    // Relationship mappings
    /*@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "superfrog")
    private List<SuperfrogCalendar> availability = new ArrayList<>();*/

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "superfrog")
    private List<Request> assignedEvents = new ArrayList<>();

    //private boolean active;


    public Student() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPerformance_rating() {
        return performance_rating;
    }

    public void setPerformance_rating(float performance_rating) {
        this.performance_rating = performance_rating;
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

    /*public List<SuperfrogCalendar> getAvailability() {
        return availability;
    }

    public void setAvailability(List<SuperfrogCalendar> availability) {
        this.availability = availability;
    }*/
    @JsonIgnore
    public List<Request> getAssignedEvents() {
        return assignedEvents;
    }

    /*public void addAssignedEvent(Request newEvent){
        this.assignedEvents.add(newEvent);
    }*/

    public void setAssignedEvents(List<Request> assignedEvents) {
        this.assignedEvents = assignedEvents;
    }

    /*public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }*/
}

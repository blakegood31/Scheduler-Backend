package superfrog_scheduler.backend.student;

import jakarta.persistence.*;
import superfrog_scheduler.backend.request.Request;
import superfrog_scheduler.backend.superfrog_calendar.SuperfrogCalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "performance")
    private float performance_rating;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},  mappedBy = "superfrog")
    private List<SuperfrogCalendar> availability = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},  mappedBy = "superfrog")
    private List<Request> assignedEvents = new ArrayList<>();

    private boolean active;


    public Student() {

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAssignedEvents(List<Request> requests) {
        this.assignedEvents = requests;
    }

    public void addAssignedEvent(Request request) {
        request.setSuperfrog(this);
        this.assignedEvents.add(request);
    }

    public void setAvailability(List<SuperfrogCalendar> availability) {
        this.availability = availability;
    }

    public void addAvailability(SuperfrogCalendar sfcAvailability) {
        sfcAvailability.setSuperfrog(this);
        this.availability.add(sfcAvailability);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPerformance_rating() {
        return performance_rating;
    }

    public void setPerformance_rating(float performance_rating) {
        this.performance_rating = performance_rating;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

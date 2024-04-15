package superfrog_scheduler.backend.superfrog_calendar;

import jakarta.persistence.*;
import superfrog_scheduler.backend.student.Student;
import java.time.LocalDateTime;
import java.io.Serializable;

@Entity
@Table(name="availability")
public class SuperfrogCalendar implements Serializable {
    @Id
    @Column(name = "entry_id")
    String id;

    @ManyToOne
    @JoinColumn(name = "superfrog_id")
    Student superfrog;

    @Column(name = "start_available")
    LocalDateTime startTime;

    @Column(name = "end_available")
    LocalDateTime endTime;

    @Column(name = "is_available")
    boolean is_available;

    public SuperfrogCalendar(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSuperfrog(Student sf) {
        this.superfrog = sf;
    }

    public Student getSuperfrog() {
        return this.superfrog;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime start_time) {
        this.startTime = start_time;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime end_time) {
        this.endTime = end_time;
    }

    public boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }
}

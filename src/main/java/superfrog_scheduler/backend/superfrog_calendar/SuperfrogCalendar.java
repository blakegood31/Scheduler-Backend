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

    /*@Column(name = "start_available")
    String start_time;*/
    @Column(name = "start_available")
    LocalDateTime start_time;


    @Column(name = "end_available")
    LocalDateTime end_time;

    @Column(name = "is_available")
    boolean is_available;

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

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }
}

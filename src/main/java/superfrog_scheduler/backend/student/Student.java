package superfrog_scheduler.backend.student;

import jakarta.persistence.*;
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

    public Student() {

    }
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

    public float getPerformance_rating() {
        return performance_rating;
    }

    public void setPerformance_rating(float performance_rating) {
        this.performance_rating = performance_rating;
    }
}

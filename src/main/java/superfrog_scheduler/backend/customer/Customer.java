package superfrog_scheduler.backend.customer;

import jakarta.persistence.*;
import superfrog_scheduler.backend.request.Request;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "customer")
public class Customer{
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private int phone;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},  mappedBy = "customer")
    private List<Request> requests = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id){this.id=id;}

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public void addRequest(Request request) {
        request.setCustomer(this);
        this.requests.add(request);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
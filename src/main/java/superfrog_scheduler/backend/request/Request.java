package superfrog_scheduler.backend.request;

import jakarta.persistence.*;
import superfrog_scheduler.backend.customer.Customer;
import superfrog_scheduler.backend.student.Student;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name= "request")
public class Request implements Serializable {

    @Id
    private String id;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "event_title")
    private String eventTitle;

    @Column(name = "status")
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "sid")
    private Student superfrog;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Customer customer;

    @Column(name = "special_instructions")
    private String specialInstructions;

    @Column(name = "other_orgs")
    private String other_orgs;

    /*private LocalDate eventDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String eventType;

    private String clientFName;

    private String clientLName;

    private String clientPhoneNumber;

    private String clientEmail;

    private String orgName;

    private Double milesFromTCU;

    private String expenses;*/

    //Constructors
    public Request(){
    }

    //Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id){this.id = id;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Student getSuperfrog() {
        return superfrog;
    }

    public void setSuperfrog(Student sup_id) {
        this.superfrog = sup_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer cust_id) {
        this.customer = cust_id;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getOther_orgs() {
        return other_orgs;
    }

    public void setOther_orgs(String other_orgs) {
        this.other_orgs = other_orgs;
    }

   /* public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getClientFName() {
        return clientFName;
    }

    public void setClientFName(String clientFName) {
        this.clientFName = clientFName;
    }

    public String getClientLName() {
        return clientLName;
    }

    public void setClientLName(String clientLName) {
        this.clientLName = clientLName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Double getMilesFromTCU() {
        return milesFromTCU;
    }

    public void setMilesFromTCU(Double milesFromTCU) {
        this.milesFromTCU = milesFromTCU;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }*/

}
package superfrog_scheduler.backend.request.dto;

import superfrog_scheduler.backend.customer.Customer;
import superfrog_scheduler.backend.request.RequestStatus;
import superfrog_scheduler.backend.student.Student;

public record RequestDto(String id, String address, String description, String event, RequestStatus status, Student sup_id, Customer cust_id, String info, String other_orgs) {
}

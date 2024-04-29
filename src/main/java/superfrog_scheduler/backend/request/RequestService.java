package superfrog_scheduler.backend.request;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.StudentRepository;
import superfrog_scheduler.backend.utils.IdWorker;
import superfrog_scheduler.backend.system.exceptions.ObjectNotFoundException;
import superfrog_scheduler.backend.request.RequestRepository;
import superfrog_scheduler.backend.request.Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RequestService {
    private final RequestRepository requestRepository;
    private final StudentRepository studentRepository;
    private final IdWorker idWorker;

    //Constructor
    public RequestService(RequestRepository requestRepository, StudentRepository studentRepository){
        this.requestRepository = requestRepository;
        this.studentRepository = studentRepository;
        this.idWorker = new IdWorker();
    }

    //Use cases
    public List<Request> findAll(){
        return this.requestRepository.findAll();
    }

    public Request findRequestById(String id){ // Find a submitted request by id
        return this.requestRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("request", id));
    }

    /*public List<Request> findByStatus(RequestStatus status){ // Find a submitted request by status
        return this.requestRepository.findByStatus(status);
    }*/

    public Request updateRequestStatus(String id, RequestStatus status){ //Update status of a request
        return this.requestRepository.findById(id).map(
                pastRequest->{
                    pastRequest.setStatus(status);
                    pastRequest.setSuperfrog(null);
                    return this.requestRepository.save(pastRequest);
                }).orElseThrow(()->new ObjectNotFoundException("request",id));
    }

    public Request updateRequestInfo(String requestId, Request update){
        return this.requestRepository.findById(requestId)
                .map(oldRequest -> {
                    oldRequest.setAddress(update.getAddress());
                    oldRequest.setDescription(update.getDescription());
                    oldRequest.setStartTime(update.getStartTime());
                    oldRequest.setEndTime(update.getEndTime());
                    oldRequest.setEventTitle(update.getEventTitle());
                    oldRequest.setStatus(update.getStatus());
                    oldRequest.setSuperfrog(update.getSuperfrog());
                    oldRequest.setCustomer(update.getCustomer());
                    oldRequest.setSpecialInstructions(update.getSpecialInstructions());
                    oldRequest.setOther_orgs(update.getOther_orgs());
                    return this.requestRepository.save(oldRequest);
                })
                .orElseThrow(() -> new ObjectNotFoundException("request", requestId));
    }

    public Request save(Request newRequest){ //Will be used to save new request
        return this.requestRepository.save(newRequest);
    }
    /*public List<Request> findRequestsByStudent(Student student) {
        return this.requestRepository.findBySuperfrog(student);
    }
    public Request updateStudentRequestStatus(String id, RequestStatus status) {
        return this.requestRepository.findById(id)
                .map(request -> {
                    // Check if the request belongs to the logged-in student
                    if (request.getSuperfrog().getId().equals(loggedInStudentId)) {
                        request.setStatus(status);
                        return this.requestRepository.save(request);
                    } else {
                        throw new UnauthorizedAccessException("You are not authorized to update this request.");
                    }
                })
                .orElseThrow(() -> new ObjectNotFoundException("request", id));
    }
    public List<Request> findApprovedRequestsNotAssigned() {
        return requestRepository.findByStatusAndSuperfrogIsNull(RequestStatus.APPROVED);
    }
    // Method to sign up for an appearance request
    public void signUpForAppearanceRequest(String requestId, String studentId) {
        // Fetch the request by ID
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("Request", requestId));

        // Fetch the student by ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ObjectNotFoundException("Student", studentId));


        // If validation passes, sign up the student for the request
        request.setSuperfrog(student);
        requestRepository.save(request);
    }
    public void cancelAppearanceSignUp(String requestId, String studentId) throws ObjectNotFoundException {
        // Fetch the assigned request by ID
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("Request", requestId));

        // Fetch the student by ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ObjectNotFoundException("Student", studentId));

        // Validate cancellation (at least 2 days prior to the event date)
        if (!isCancellationAllowed(request.getStartTime())) {
            throw new ObjectNotFoundException("Cancellation", "Cancellation is not allowed less than 2 days before the event.");
        }

        // Update request status to "Approved" and remove from student's schedule
        request.setStatus(RequestStatus.APPROVED);
        request.setSuperfrog(null); // Remove SuperFrog Student assignment
        requestRepository.save(request);
        // Notify relevant actors about cancellation
        // Implement notification logic here
    }




    // Method to check if cancellation is allowed (at least 2 days prior to the event date)
    private boolean isCancellationAllowed(LocalDateTime eventDateTime) {
        // Calculate the difference in days between current date and event date
        LocalDate currentDate = LocalDate.now();
        LocalDate eventDate = eventDateTime.toLocalDate();
        long daysDifference = java.time.temporal.ChronoUnit.DAYS.between(currentDate, eventDate);

        // Cancellation is allowed if the difference is at least 2 days
        return daysDifference >= 2;
    }
    public void markAppearanceRequestCompleted(String requestId) {
        // Fetch the request by ID
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("Request", requestId));

        // Ensure the request is in the assigned status
        if (request.getStatus() != RequestStatus.ASSIGNED) {
            throw new IllegalStateException("The request must be in the 'Assigned' status to mark it as completed.");
        }

        // Check if the event has finished
        LocalDateTime currentTime = LocalDateTime.now();
        if (request.getEndTime().isAfter(currentTime)) {
            throw new IllegalStateException("The event has not finished yet.");
        }

        // Update status to completed
        request.setStatus(RequestStatus.COMPLETED);
        requestRepository.save(request);

        // Notify relevant actors (Spirit Director and Customer)
        // Implement notification logic here
    }
    public Request markAppearanceAsIncomplete(String requestId) {
        // Fetch the request by ID
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("Request", requestId));

        // Check if the request status is either Assigned or Completed
        if (request.getStatus() != RequestStatus.ASSIGNED && request.getStatus() != RequestStatus.COMPLETED) {
            throw new IllegalStateException("The request must be in the 'Assigned' or 'Completed' status to mark it as incomplete.");
        }

        // Check if the event has finished
        LocalDateTime currentTime = LocalDateTime.now();
        if (request.getEndTime().isAfter(currentTime)) {
            throw new IllegalStateException("It is too early to confirm the appearance is incomplete.");
        }

        // Update status to incomplete
        request.setStatus(RequestStatus.INCOMPLETE);
        return requestRepository.save(request);
    }

    public Request reverseApprovalRejectionDecision(String requestId) {
        // Fetch the request by ID
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("Request", requestId));

        // Reverse the approval/rejection decision
        if (request.getStatus() == RequestStatus.APPROVED) {
            // Rejection flow
            request.setStatus(RequestStatus.REJECTED);
            // Additional logic if needed
        } else if (request.getStatus() == RequestStatus.REJECTED) {
            // Approval flow
            request.setStatus(RequestStatus.APPROVED);
            // Additional logic if needed
        }

        // Save the updated request
        return requestRepository.save(request);
    } */
}
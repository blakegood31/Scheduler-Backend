
package superfrog_scheduler.backend.request;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import superfrog_scheduler.backend.request.converter.RequestDtoToRequestConverter;
import superfrog_scheduler.backend.request.converter.RequestToRequestDtoConverter;
import superfrog_scheduler.backend.request.dto.RequestDto;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.student.StudentRepository;
import superfrog_scheduler.backend.system.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RequestController {

    private final RequestService requestService;
    private final RequestToRequestDtoConverter requestToRequestDtoConverter;
    private final RequestDtoToRequestConverter requestDtoToRequestConverter;
    private final StudentRepository studentRepository;

    //Constructor
    public RequestController(RequestService requestService, RequestToRequestDtoConverter requestToRequestDtoConverter, RequestDtoToRequestConverter requestDtoToRequestConverter, StudentRepository studentRepository) {
        this.requestService = requestService;
        this.requestToRequestDtoConverter = requestToRequestDtoConverter;
        this.requestDtoToRequestConverter = requestDtoToRequestConverter;
        this.studentRepository = studentRepository; // Initialize studentRepository
    }


    //Signature Handler methods
    @GetMapping("/requests")
    public Result findAllRequests() {
        List<Request> requestDtos = this.requestService.findAll();
        requestDtos.stream()
                .map(this.requestToRequestDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all success", requestDtos);
    }

    @GetMapping("/requests/{id}") // Find a submitted request by id
    public Result findRequestById(@PathVariable("id") String id) {
        Request foundRequest = this.requestService.findRequestById(id);
        RequestDto requestDto = this.requestToRequestDtoConverter.convert(foundRequest);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", requestDto);
    }

    @PutMapping("/request/{id}/status/{status}") // Update status of a request
    public Result updateRequestStatus(@PathVariable String id, @PathVariable RequestStatus status) {
        Request updateRequest = this.requestService.updateRequestStatus(id, status);
        RequestDto updateRequestDto = this.requestToRequestDtoConverter.convert(updateRequest);
        return new Result(true, StatusCode.SUCCESS, "Status Update Success", updateRequestDto);
    }

    @PostMapping("/request")
    public Result addRequest(@RequestBody RequestDto requestDto) { // Add a new request
        Request newRequest = this.requestDtoToRequestConverter.convert(requestDto);
        Request savedRequest = this.requestService.save(newRequest);
        RequestDto saveRequestDto = this.requestToRequestDtoConverter.convert(savedRequest);
        return new Result(true, StatusCode.SUCCESS, "Add Success", saveRequestDto);
    }

    @PutMapping("/requests/{requestId}")
    public Result updateArtifact(@PathVariable String requestId, @Valid @RequestBody RequestDto requestDto) {
        Request update = this.requestDtoToRequestConverter.convert(requestDto);
        Request updatedRequest = this.requestService.updateRequestInfo(requestId, update);
        RequestDto updatedRequestDto = this.requestToRequestDtoConverter.convert(updatedRequest);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedRequestDto);
    }

    @GetMapping("/student/requests")
    public Result findStudentAssignedRequests(@RequestParam("studentId") String studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            List<Request> assignedRequests = this.requestService.findRequestsByStudent(student);
            List<RequestDto> assignedRequestDtos = assignedRequests.stream()
                    .map(this.requestToRequestDtoConverter::convert)
                    .collect(Collectors.toList());
            return new Result(true, StatusCode.SUCCESS, "Assigned Requests Found", assignedRequestDtos);
        } else {
            return new Result(false, StatusCode.NOT_FOUND, "Student not found", null);
        }
    }

    @PutMapping("/student/request/{id}/status/{status}")
    public Result updateStudentRequestStatus(@PathVariable String id, @PathVariable RequestStatus status) {
        Request updatedRequest = this.requestService.updateStudentRequestStatus(id, status);
        RequestDto updatedRequestDto = this.requestToRequestDtoConverter.convert(updatedRequest);
        return new Result(true, StatusCode.SUCCESS, "Status Update Success", updatedRequestDto);
    }

    @GetMapping("/appearance-requests/available")
    public Result findAvailableAppearanceRequests() {
        List<Request> availableRequests = requestService.findApprovedRequestsNotAssigned();
        List<RequestDto> requestDtos = availableRequests.stream()
                .map(requestToRequestDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Available Appearance Requests Found", requestDtos);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpForAppearanceRequest(@RequestParam("requestId") String requestId,
                                                             @RequestParam("studentId") String studentId) {
        requestService.signUpForAppearanceRequest(requestId, studentId);
        return ResponseEntity.ok("Signed up successfully");
    }

    @PutMapping("/cancel-appearance-signup/{requestId}/{studentId}")
    public ResponseEntity<String> cancelAppearanceSignUp(@PathVariable String requestId, @PathVariable String studentId) {
        try {
            // Call the service method to cancel appearance sign-up
            requestService.cancelAppearanceSignUp(requestId, studentId);
            return ResponseEntity.ok("Cancellation successful");
        } catch (ObjectNotFoundException e) {
            // Handle exception and return appropriate response
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/requests/{requestId}/complete")
    public Result markRequestAsCompleted(@PathVariable String requestId) {
        try {
            requestService.markAppearanceRequestCompleted(requestId);
            return new Result(true, StatusCode.SUCCESS, "Appearance request marked as completed successfully", null);
        } catch (ObjectNotFoundException e) {
            return new Result(false, StatusCode.NOT_FOUND, e.getMessage(), null);
        }
    }
    @PutMapping("/requests/{requestId}/incomplete")
    public Result markRequestAsIncomplete(@PathVariable String requestId) {
        try {
            requestService.markAppearanceAsIncomplete(requestId);
            return new Result(true, StatusCode.SUCCESS, "Appearance request marked as incomplete successfully", null);
        } catch (ObjectNotFoundException e) {
            return new Result(false, StatusCode.NOT_FOUND, e.getMessage(), null);
        }
    }

    @PutMapping("/requests/{requestId}/reverse-decision")
    public Result reverseApprovalRejectionDecision(@PathVariable String requestId) {
        try {
            Request updatedRequest = requestService.reverseApprovalRejectionDecision(requestId);
            RequestDto updatedRequestDto = requestToRequestDtoConverter.convert(updatedRequest);
            return new Result(true, StatusCode.SUCCESS, "Approval/rejection decision reversed successfully", updatedRequestDto);
        } catch (ObjectNotFoundException e) {
            return new Result(false, StatusCode.NOT_FOUND, e.getMessage(), null);
        }
    }

}

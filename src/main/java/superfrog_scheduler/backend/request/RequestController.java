
package superfrog_scheduler.backend.request;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import superfrog_scheduler.backend.request.converter.RequestDtoToRequestConverter;
import superfrog_scheduler.backend.request.converter.RequestToRequestDtoConverter;
import superfrog_scheduler.backend.request.dto.RequestDto;
import superfrog_scheduler.backend.student.StudentService;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;
import superfrog_scheduler.backend.student.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RequestController {

    private final RequestService requestService;
    private final RequestToRequestDtoConverter requestToRequestDtoConverter;
    private final RequestDtoToRequestConverter requestDtoToRequestConverter;
    private final StudentRepository studentRepository;

    private final StudentService studentService;

    //Constructor
    public RequestController(RequestService requestService, RequestToRequestDtoConverter requestToRequestDtoConverter, RequestDtoToRequestConverter requestDtoToRequestConverter, StudentRepository studentRepository, StudentService studentService) {
        this.requestService = requestService;
        this.requestToRequestDtoConverter = requestToRequestDtoConverter;
        this.requestDtoToRequestConverter = requestDtoToRequestConverter;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }


    //Signature Handler methods
    @GetMapping("/requests")
    public Result findAllRequests(){
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

    @PutMapping("/requests/{id}/status/{status}") // Update status of a request
    public Result updateRequestStatus(@PathVariable String id,@PathVariable RequestStatus status) {
        Request updateRequest = this.requestService.updateRequestStatus(id, status);
        RequestDto updateRequestDto = this.requestToRequestDtoConverter.convert(updateRequest);
        return new Result(true,  StatusCode.SUCCESS, "Status Update Success", updateRequestDto);
    }

    @PostMapping("/requests")
    public Result addRequest(@RequestBody RequestDto requestDto) { // Add a new request
        Request newRequest = this.requestDtoToRequestConverter.convert(requestDto);
        Request savedRequest = this.requestService.save(newRequest);
        RequestDto saveRequestDto = this.requestToRequestDtoConverter.convert(savedRequest);
        return new Result(true, StatusCode.SUCCESS, "Add Success", saveRequestDto);
    }

    @PutMapping("/requests/{requestId}")
    public Result updateRequest(@PathVariable String requestId, @Validated @RequestBody RequestDto requestDto){
        Request update = this.requestDtoToRequestConverter.convert(requestDto);
        Request updatedRequest = this.requestService.updateRequestInfo(requestId, update);
        RequestDto updatedRequestDto = this.requestToRequestDtoConverter.convert(updatedRequest);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedRequestDto);
    }

    @GetMapping("/requests/approved")
    public Result findApprovedRequests(){
        List<Request> approvedRequestDtos = this.requestService.findAll();
        approvedRequestDtos.removeIf(request ->
            !(request.getStatus() == RequestStatus.APPROVED || request.getStatus() == RequestStatus.ASSIGNED)
        );
        approvedRequestDtos.stream()
                .map(this.requestToRequestDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all success", approvedRequestDtos);
    }

    @PutMapping("/requests/{requestId}/cancel")
    public Result cancelRequest(@PathVariable String requestId){
        Request canceledRequest = this.requestService.updateRequestStatus(requestId, RequestStatus.CANCELLED);
        RequestDto canceledRequestDto = this.requestToRequestDtoConverter.convert(canceledRequest);
        return new Result(true, StatusCode.SUCCESS, "Cancel Request Success", canceledRequestDto);
    }


    /*@GetMapping("/student/requests")
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

    }*/

}

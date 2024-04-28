package superfrog_scheduler.backend;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import superfrog_scheduler.backend.request.Request;
import superfrog_scheduler.backend.request.RequestRepository;
import superfrog_scheduler.backend.request.RequestService;
import superfrog_scheduler.backend.request.RequestStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RequestServiceTest {

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private RequestService requestService;

    // Initialize mocks
    public RequestServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReverseApprovalRejectionDecision() {
        // Create a sample request
        Request request = new Request();
        request.setId("1");
        request.setStatus(RequestStatus.APPROVED); // Assume the request is initially approved

        // Mock the behavior of the request repository findById method
        when(requestRepository.findById("1")).thenReturn(java.util.Optional.of(request));

        // Call the method to reverse the approval/rejection decision
        Request updatedRequest = requestService.reverseApprovalRejectionDecision("1");

        // Verify that the status is now reversed
        assertEquals(RequestStatus.REJECTED, updatedRequest.getStatus());

        // Verify that the save method was called once
        verify(requestRepository, times(1)).save(request);
    }
}

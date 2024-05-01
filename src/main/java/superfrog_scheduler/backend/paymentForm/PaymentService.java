package superfrog_scheduler.backend.paymentForm;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.paymentForm.dto.Period;
import superfrog_scheduler.backend.request.Request;
import superfrog_scheduler.backend.request.RequestRepository;
import superfrog_scheduler.backend.student.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private RequestRepository requestRepository;

    private PaymentFormRepository paymentFormRepository;


    public PaymentService(RequestRepository requestRepository, PaymentFormRepository paymentFormRepository) {
        this.requestRepository = requestRepository;
        this.paymentFormRepository = paymentFormRepository;
    }


    public List<PaymentForm> generatePaymentForms(List<String> IdList, Period paymentPeriod) {
        List<Request> selectedRequests = this.requestRepository.findByIdIn(IdList);

        Map<Student, List<Request>> studentRequestsMap = groupRequestsByStudent(selectedRequests);

        // For each SuperFrogStudent, generate a payment form, and then collect the payment forms into a list.
        List<PaymentForm> paymentForms = studentRequestsMap.entrySet().stream()
                .map(entry -> entry.getKey().generatePaymentForm(entry.getValue(), paymentPeriod))
                .collect(Collectors.toList());

        // Persist the generated payment forms and then return them.
        return this.paymentFormRepository.saveAll(paymentForms);
    }

    private Map<Student, List<Request>> groupRequestsByStudent(List<Request> selectedRequests) {
        Map<Student, List<Request>> studentRequestsMap = selectedRequests.stream()
                .collect(Collectors.groupingBy(Request::getSuperfrog));
        return studentRequestsMap;
    }

}
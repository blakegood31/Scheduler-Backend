package superfrog_scheduler.backend.paymentForm;

import org.springframework.web.bind.annotation.*;
import superfrog_scheduler.backend.paymentForm.dto.Period;
import superfrog_scheduler.backend.paymentForm.dto.RequestIds;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;


import java.util.List;

@RestController
public class PaymentController {
    private PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/paymentform")
    public Result generatePaymentForms(@RequestBody RequestIds requestIds) {
        List<String> selectedIds = requestIds.getRequestIds();

        Period paymentPeriod = requestIds.getPaymentPeriod();

        List<PaymentForm> paymentForms = this.paymentService.generatePaymentForms(selectedIds, paymentPeriod);

        return new Result(true, StatusCode.SUCCESS, "Payment Forms are Generated Successfully.", paymentForms);
    }
}

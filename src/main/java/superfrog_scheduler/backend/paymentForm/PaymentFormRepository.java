package superfrog_scheduler.backend.paymentForm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentFormRepository extends JpaRepository<PaymentForm, Integer>{
}
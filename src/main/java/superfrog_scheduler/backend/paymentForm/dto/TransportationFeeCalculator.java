package superfrog_scheduler.backend.paymentForm.dto;

import superfrog_scheduler.backend.request.Request;

import java.math.BigDecimal;
import java.util.List;

public enum TransportationFeeCalculator {
    INSTANCE;

    private final Double FREE_MILEAGE = 2.0; // The first 2.0 miles are free of charge.

    private final Double TRANSPORTATION_RATE = 0.75; // 0.75 per mile for extra mileage.


    public BigDecimal calculateTransportationFee(List<Request> requests) {
        Double totalExtraMileage = requests.stream()
                .map(request -> request.getMileageOver(this.FREE_MILEAGE)) // Map to Double stream.
                .reduce(0.0, (subtotal, extraMileage) -> subtotal + extraMileage); // The first FREE_MILEAGE miles are free of charge.

        BigDecimal transportationFee = BigDecimal.valueOf(this.TRANSPORTATION_RATE).multiply(BigDecimal.valueOf(totalExtraMileage));
        return transportationFee;
    }
}

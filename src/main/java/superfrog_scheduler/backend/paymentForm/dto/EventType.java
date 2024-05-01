package superfrog_scheduler.backend.paymentForm.dto;

public enum EventType {
    TCU(100), PUBLIC(100), PRIVATE(175);

    private int hourlyRate;


    EventType(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

}
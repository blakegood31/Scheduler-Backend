package superfrog_scheduler.backend.superfrog_calendar;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.utils.IdWorker;

import java.util.List;

@Service
@Transactional
public class SuperfrogCalendarService {

    private final SuperfrogCalendarRepository sfcRepository;

    private final IdWorker idWorker;

    public SuperfrogCalendarService(SuperfrogCalendarRepository sfcRepository) {
        this.sfcRepository = sfcRepository;
        this.idWorker = new IdWorker();
    }

    public List<SuperfrogCalendar> findAll(){
        return this.sfcRepository.findAll();
    }
}

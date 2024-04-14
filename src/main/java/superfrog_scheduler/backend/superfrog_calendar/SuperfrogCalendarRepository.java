package superfrog_scheduler.backend.superfrog_calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperfrogCalendarRepository extends JpaRepository<SuperfrogCalendar, String> {
}

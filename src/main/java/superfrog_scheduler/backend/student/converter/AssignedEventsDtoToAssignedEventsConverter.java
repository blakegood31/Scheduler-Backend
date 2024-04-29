package superfrog_scheduler.backend.student.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.request.Request;
import superfrog_scheduler.backend.student.dto.AssignedEventsDto;
import java.util.List;

@Component
public class AssignedEventsDtoToAssignedEventsConverter implements Converter<AssignedEventsDto, List<Request>> {
    @Override
    public List<Request> convert(AssignedEventsDto source) {
        return source.assignedEvents();
    }
}

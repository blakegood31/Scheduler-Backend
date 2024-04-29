package superfrog_scheduler.backend.student.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.request.Request;
import superfrog_scheduler.backend.student.dto.AssignedEventsDto;

import java.util.List;

@Component
public class AssignedEventsToAssignedEventsDtoConverter implements Converter<List<Request>, AssignedEventsDto> {
    @Override
    public AssignedEventsDto convert(List<Request> source) {
        return new AssignedEventsDto(source);
    }
}

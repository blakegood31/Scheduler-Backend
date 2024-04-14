package superfrog_scheduler.backend.superfrog_calendar.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.superfrog_calendar.SuperfrogCalendar;
import superfrog_scheduler.backend.superfrog_calendar.dto.SuperfrogCalendarDto;

@Component
public class SfCalendarToSfCalendarDtoConverter implements Converter<SuperfrogCalendar, SuperfrogCalendarDto> {

    @Override
    public SuperfrogCalendarDto convert(SuperfrogCalendar source){
        SuperfrogCalendarDto sfcDto = new SuperfrogCalendarDto(
                source.getId(),
                source.getSuperfrog(),
                source.getStart_time(),
                source.getEnd_time(),
                source.getIs_available()
        );
        return sfcDto;
    }

}

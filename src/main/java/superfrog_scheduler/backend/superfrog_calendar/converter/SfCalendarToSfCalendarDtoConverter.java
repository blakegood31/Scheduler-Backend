package superfrog_scheduler.backend.superfrog_calendar.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.superfrog_calendar.SuperfrogCalendar;
import superfrog_scheduler.backend.superfrog_calendar.dto.SuperfrogCalendarDto;

import java.time.format.DateTimeFormatter;

@Component
public class SfCalendarToSfCalendarDtoConverter implements Converter<SuperfrogCalendar, SuperfrogCalendarDto> {

    @Override
    public SuperfrogCalendarDto convert(SuperfrogCalendar source){
        SuperfrogCalendarDto sfcDto = new SuperfrogCalendarDto(
                source.getId(),
                source.getSuperfrog(),
                source.getStartTime(),
                source.getEndTime(),
                source.getIs_available()
        );
        return sfcDto;
    }

}

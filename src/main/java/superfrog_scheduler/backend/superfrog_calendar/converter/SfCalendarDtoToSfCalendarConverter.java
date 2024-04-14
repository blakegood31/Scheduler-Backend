package superfrog_scheduler.backend.superfrog_calendar.converter;

import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.superfrog_calendar.SuperfrogCalendar;
import superfrog_scheduler.backend.superfrog_calendar.SuperfrogCalendarController;
import superfrog_scheduler.backend.superfrog_calendar.dto.SuperfrogCalendarDto;
import org.springframework.core.convert.converter.Converter;

@Component
public class SfCalendarDtoToSfCalendarConverter implements Converter<SuperfrogCalendarDto, SuperfrogCalendar> {

    @Override
    public SuperfrogCalendar convert(SuperfrogCalendarDto source){
        SuperfrogCalendar sfc = new SuperfrogCalendar();
        sfc.setId(source.id());
        sfc.setSuperfrog(source.superfrog());
        sfc.setStart_time(source.start_time());
        sfc.setEnd_time(source.end_time());
        sfc.setIs_available(source.is_available());
        return sfc;
    }


}

package superfrog_scheduler.backend.superfrog_calendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import superfrog_scheduler.backend.superfrog_calendar.converter.SfCalendarDtoToSfCalendarConverter;
import superfrog_scheduler.backend.superfrog_calendar.converter.SfCalendarToSfCalendarDtoConverter;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SuperfrogCalendarController {

    private final SfCalendarToSfCalendarDtoConverter sfCalendarToSfCalendarDtoConverter;

    private final SfCalendarDtoToSfCalendarConverter sfCalendarDtoToSfCalendarConverter;

    private final SuperfrogCalendarService superfrogCalendarService;

    public SuperfrogCalendarController(SfCalendarToSfCalendarDtoConverter sfCalendarToSfCalendarDtoConverter, SfCalendarDtoToSfCalendarConverter sfCalendarDtoToSfCalendarConverter, SuperfrogCalendarService superfrogCalendarService) {
        this.sfCalendarToSfCalendarDtoConverter = sfCalendarToSfCalendarDtoConverter;
        this.sfCalendarDtoToSfCalendarConverter = sfCalendarDtoToSfCalendarConverter;
        this.superfrogCalendarService = superfrogCalendarService;
    }

    @GetMapping("/availability")
    public Result findAllAvailable(){
        List<SuperfrogCalendar> sfcDtos = this.superfrogCalendarService.findAll();
        sfcDtos.stream()
                .map(this.sfCalendarToSfCalendarDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all success", sfcDtos);
    }
}

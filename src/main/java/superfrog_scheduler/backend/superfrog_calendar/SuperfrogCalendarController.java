package superfrog_scheduler.backend.superfrog_calendar;

import org.springframework.web.bind.annotation.*;
import superfrog_scheduler.backend.superfrog_calendar.converter.SfCalendarDtoToSfCalendarConverter;
import superfrog_scheduler.backend.superfrog_calendar.converter.SfCalendarToSfCalendarDtoConverter;
import superfrog_scheduler.backend.superfrog_calendar.dto.SuperfrogCalendarDto;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/availability")
public class SuperfrogCalendarController {

    private final SfCalendarToSfCalendarDtoConverter sfCalendarToSfCalendarDtoConverter;

    private final SfCalendarDtoToSfCalendarConverter sfCalendarDtoToSfCalendarConverter;

    private final SuperfrogCalendarService superfrogCalendarService;

    public SuperfrogCalendarController(SfCalendarToSfCalendarDtoConverter sfCalendarToSfCalendarDtoConverter, SfCalendarDtoToSfCalendarConverter sfCalendarDtoToSfCalendarConverter, SuperfrogCalendarService superfrogCalendarService) {
        this.sfCalendarToSfCalendarDtoConverter = sfCalendarToSfCalendarDtoConverter;
        this.sfCalendarDtoToSfCalendarConverter = sfCalendarDtoToSfCalendarConverter;
        this.superfrogCalendarService = superfrogCalendarService;
    }

    @GetMapping
    public Result findAllAvailable(){
        List<SuperfrogCalendar> sfcDtos = this.superfrogCalendarService.findAll();
        sfcDtos.stream()
                .map(this.sfCalendarToSfCalendarDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all success", sfcDtos);
    }

    @PostMapping
    public Result addAvailability(@RequestBody SuperfrogCalendarDto sfcDto){
        SuperfrogCalendar newAvailability = this.sfCalendarDtoToSfCalendarConverter.convert(sfcDto);
        SuperfrogCalendar savedAvailability = this.superfrogCalendarService.save(newAvailability);
        SuperfrogCalendarDto savedSfcDto = this.sfCalendarToSfCalendarDtoConverter.convert(savedAvailability);
        return new Result(true, StatusCode.SUCCESS, "Add availability success", savedSfcDto);
    }
}

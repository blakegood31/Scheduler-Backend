package superfrog_scheduler.backend.request.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.request.Request;
import superfrog_scheduler.backend.request.dto.RequestDto;

@Component
public class RequestToRequestDtoConverter implements Converter<Request, RequestDto> {

    @Override
    public RequestDto convert(Request source){
        RequestDto request = new RequestDto(
                source.getId(),
                source.getAddress(),
                source.getDescription(),
                source.getStartTime(),
                source.getEndTime(),
                source.getEventTitle(),
                source.getStatus(),
                source.getSuperfrog(),
                source.getCustomer(),
                source.getSpecialInstructions(),
                source.getOther_orgs(),
                source.getEventType(),
                source.getMilesFromTCU()
        );
        return request;
    }
}

package superfrog_scheduler.backend.director.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.director.Director;
import superfrog_scheduler.backend.director.dto.DirectorDto;

@Component
public class DirDtoToDirConverter implements Converter<DirectorDto, Director>{
    @Override
    public Director convert(DirectorDto source) {
        Director director = new Director();
        director.setEmail(source.email());
        director.setActive(source.active());
        director.setRoles(source.roles());
        return director;
    }
}

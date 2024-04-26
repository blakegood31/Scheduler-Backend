package superfrog_scheduler.backend.director.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.director.Director;
import superfrog_scheduler.backend.director.dto.DirectorDto;

@Component
public class DirToDirDtoConverter implements Converter<Director, DirectorDto> {
    @Override
    public DirectorDto convert(Director source) {
        final DirectorDto directorDto = new DirectorDto(
                source.getId(),
                source.getName());
                /*source.getEmail(),
                source.isActive(),
                source.getRoles());*/
        return directorDto;
    }
}

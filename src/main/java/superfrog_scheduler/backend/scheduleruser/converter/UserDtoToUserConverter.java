package superfrog_scheduler.backend.scheduleruser.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.scheduleruser.User;
import superfrog_scheduler.backend.scheduleruser.dto.UserDto;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto source){
        User user = new User();
        user.setUsername(source.username());
        user.setEnabled(source.enabled());
        user.setRoles(source.roles());
        return user;
    }
}

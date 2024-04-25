package superfrog_scheduler.backend.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.scheduleruser.MyUserPrincipal;
import superfrog_scheduler.backend.scheduleruser.User;
import superfrog_scheduler.backend.scheduleruser.converter.UserToUserDtoConverter;
import superfrog_scheduler.backend.scheduleruser.dto.UserDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    private final UserToUserDtoConverter userToUserDtoConverter;
    public AuthService(JwtProvider jwtProvider, UserToUserDtoConverter userToUserDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        // create user info
        MyUserPrincipal principal = (MyUserPrincipal)authentication.getPrincipal();
        User user = principal.getUser();
        UserDto userDto = this.userToUserDtoConverter.convert(user);

        // create a JWT
        String token = this.jwtProvider.createToken(authentication);

        Map<String, Object> loginResultMap = new HashMap<>();
        loginResultMap.put("userInfo", userDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}

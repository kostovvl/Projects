package exam.prep.service;

import exam.prep.domain.dto.UserDto;

public interface UserService {

    UserDto findByUsername(String username);

    void seedUser(UserDto userDto);

}

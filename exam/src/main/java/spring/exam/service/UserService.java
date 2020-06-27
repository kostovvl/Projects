package spring.exam.service;

import spring.exam.domain.dto.UserDto;

public interface UserService {

    UserDto findByUsername(String username);
    void seedUser(UserDto userDto);

}

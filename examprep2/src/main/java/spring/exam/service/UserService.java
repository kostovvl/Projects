package spring.exam.service;

import spring.exam.domain.dto.UserDto;

public interface UserService {

    void registerUser(UserDto userDto);

    UserDto findByUsername(String username);

}

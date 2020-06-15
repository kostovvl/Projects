package javaweb.workshop.service;

import javaweb.workshop.domain.dto.UserDto;
import javaweb.workshop.domain.entity.User;

import java.util.Optional;

public interface UserService {

    void registerUser(UserDto userDto);
    UserDto findByUsername(String username);
}

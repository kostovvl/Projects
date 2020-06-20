package springfundamentals.examprep.service;

import springfundamentals.examprep.domain.dto.UserDto;

public interface UserService {

    void registerUser(UserDto userDto);

    UserDto findByUsername(String username);

}

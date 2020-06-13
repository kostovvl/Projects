package springfundamentals.lab2.service;

import springfundamentals.lab2.domain.dto.UserGetDto;
import springfundamentals.lab2.domain.dto.UserSetDto;
import springfundamentals.lab2.exception.EntityExistsException;

import java.util.List;

public interface UserService {

    void registerUser(UserSetDto userSetDto) throws EntityExistsException;
    List<UserGetDto> findAllUsers();

    UserGetDto findByUsername(String username);

}

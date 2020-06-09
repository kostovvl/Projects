package springfundamentals.lab.service;

import springfundamentals.lab.domain.dto.UserGetDto;
import springfundamentals.lab.domain.dto.UserSetDto;
import springfundamentals.lab.exception.EntityExistsException;

import java.util.List;

public interface UserService {

    void registerUser(UserSetDto userSetDto) throws EntityExistsException;
    List<UserGetDto> findAllUsers();

}

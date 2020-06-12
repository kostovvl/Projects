package springfundamentals.lab2.service;

import springfundamentals.lab2.domain.dto.UserGetDto;
import springfundamentals.lab2.domain.entity.User;
import springfundamentals.lab2.exception.EntityExistsException;

import java.util.List;

public interface UserService {

    void registerUser(User user) throws EntityExistsException;
    List<UserGetDto> findAllUsers();
    public User findByUsername(String username);

}

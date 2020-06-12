package springfundamentals.lab2.service;

import springfundamentals.lab2.domain.entity.User;

public interface AuthService {

    boolean UserExists(String username, String password);

}

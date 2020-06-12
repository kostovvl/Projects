package springfundamentals.lab2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfundamentals.lab2.domain.entity.User;
import springfundamentals.lab2.repository.UserRepository;
import springfundamentals.lab2.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean UserExists(String username, String password) {

        User existing = this.userRepository.findByUsername(username);
        return existing != null && existing.getPassword().equals(password);
    }
}

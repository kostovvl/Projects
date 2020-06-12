package springfundamentals.lab2.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springfundamentals.lab2.domain.dto.UserGetDto;
import springfundamentals.lab2.domain.dto.UserSetDto;
import springfundamentals.lab2.domain.entity.Role;
import springfundamentals.lab2.domain.entity.User;
import springfundamentals.lab2.exception.EntityExistsException;
import springfundamentals.lab2.repository.UserRepository;
import springfundamentals.lab2.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void registerUser(User user) throws EntityExistsException {

        if (this.userRepository.findByUsername(user.getUsername()) != null) {
            throw new EntityExistsException();
        }

        if (this.userRepository.count() == 0) {
            user.setRole(Role.valueOf("ADMIN"));
        } else {
            user.setRole(Role.valueOf("USER"));
        }

        user.setActive(true);
        user.setCreated(new Date());
        user.setModified(new Date());

        this.userRepository.saveAndFlush(user);

    }

    @Override
    public List<UserGetDto> findAllUsers() {
        return this.userRepository.findAll().stream()
                .map(u -> this.mapper.map(u, UserGetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}

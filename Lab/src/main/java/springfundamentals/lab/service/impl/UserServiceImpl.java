package springfundamentals.lab.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springfundamentals.lab.domain.dto.UserGetDto;
import springfundamentals.lab.domain.dto.UserSetDto;
import springfundamentals.lab.domain.entity.Role;
import springfundamentals.lab.domain.entity.User;
import springfundamentals.lab.exception.EntityExistsException;
import springfundamentals.lab.repository.UserRepository;
import springfundamentals.lab.service.UserService;

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
    public void registerUser(UserSetDto userSetDto) throws EntityExistsException {

        if (this.userRepository.findByUsername(userSetDto.getUsername()) != null) {
            throw new EntityExistsException();
        }

        User user = this.mapper.map(userSetDto, User.class);

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
}

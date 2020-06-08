package springfundamentals.lab.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springfundamentals.lab.domain.dto.UserSetDto;
import springfundamentals.lab.domain.entity.Role;
import springfundamentals.lab.domain.entity.User;
import springfundamentals.lab.repository.UserRepository;
import springfundamentals.lab.service.UserService;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void registerUser(UserSetDto userSetDto) {
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
}

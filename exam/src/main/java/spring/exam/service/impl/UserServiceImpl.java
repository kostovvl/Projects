package spring.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.exam.domain.dto.UserDto;
import spring.exam.domain.entity.User;
import spring.exam.repository.UserRepository;
import spring.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDto findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .map(u -> this.mapper.map(u, UserDto.class))
                .orElse(null);
    }

    @Override
    public void seedUser(UserDto userDto) {
        this.userRepository.saveAndFlush(this.mapper.map(userDto, User.class));
    }
}

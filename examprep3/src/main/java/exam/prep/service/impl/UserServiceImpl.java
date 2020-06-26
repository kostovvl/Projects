package exam.prep.service.impl;

import exam.prep.domain.dto.UserDto;
import exam.prep.domain.entity.User;
import exam.prep.repository.UserRepository;
import exam.prep.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

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

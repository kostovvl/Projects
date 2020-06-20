package springfundamentals.examprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfundamentals.examprep.domain.dto.UserDto;
import springfundamentals.examprep.domain.entity.User;
import springfundamentals.examprep.repository.UserRepository;
import springfundamentals.examprep.service.UserService;

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
    public void registerUser(UserDto userDto) {
         this.userRepository.saveAndFlush(this.mapper.map(userDto, User.class));

    }

    @Override
    public UserDto findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .map(u -> this.mapper.map(u, UserDto.class)).orElse(null);
    }
}

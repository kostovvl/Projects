package javaweb.workshop.service.impl;

import javaweb.workshop.domain.dto.RoleDto;
import javaweb.workshop.domain.dto.UserDto;
import javaweb.workshop.domain.entity.Role;
import javaweb.workshop.domain.entity.User;
import javaweb.workshop.repository.UserRepository;
import javaweb.workshop.service.RoleService;
import javaweb.workshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.mapper = mapper;
    }


    @Override
    public void registerUser(UserDto userDto) {
        RoleDto roleDto;
        if (this.userRepository.count() == 0) {
            roleDto = this.roleService.extractRole("ADMIN");
        } else {
            roleDto = this.roleService.extractRole("USER");
        }
        userDto.setRole(roleDto);

        this.userRepository.saveAndFlush(this.mapper.map(userDto, User.class));
    }

    @Override
    public UserDto findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .map(u -> this.mapper.map(u, UserDto.class))
                .orElse(null);
    }

    @Override
    public List<String> findAllUsernames() {

        return this.userRepository.findAll().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    @Override
    public void changeRole(String username, String role) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        assert user != null;
        if (!user.getRole().getName().equals(role)) {
            Role newRole = this.mapper.map(this.roleService.extractRole(role), Role.class);
            user.setRole(newRole);
        }
        this.userRepository.saveAndFlush(user);
    }
}

package softuni.workshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.entity.User;
import softuni.workshop.domain.service.LoginUserService;
import softuni.workshop.domain.service.SetUserService;
import softuni.workshop.repository.UserRepository;
import softuni.workshop.service.RoleService;
import softuni.workshop.service.UserService;

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
    public void seedUser(SetUserService userService) {

        userService.setRole(this.roleService.getRole(
                this.userRepository.count() == 0 ? "ADMIN" : "USER"
        ));

        User user = this.mapper.map(userService, User.class);

        this.userRepository.saveAndFlush(user);

    }

    @Override
    public boolean exists(LoginUserService loginUserService) {
        return this.userRepository.findByUsernameAndPassword(loginUserService.getUsername(), loginUserService.getPassword()) != null;
    }
}

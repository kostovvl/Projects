package javaweb.workshop.service.impl;

import javaweb.workshop.domain.entity.Role;
import javaweb.workshop.domain.entity.User;
import javaweb.workshop.domain.service.LoginUserService;
import javaweb.workshop.domain.service.SetRoleService;
import javaweb.workshop.domain.service.SetUserService;
import javaweb.workshop.repository.UserRepository;
import javaweb.workshop.service.RoleService;
import javaweb.workshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        userService.setRole(this.roleService.setRole(
                this.userRepository.count() == 0 ? "ADMIN" : "USER"));

        this.userRepository.saveAndFlush(this.mapper.map(userService, User.class));

    }

    @Override
    public boolean userExists(SetUserService userService) {
        return this.userRepository.findByUsernameAndPassword(userService.getUsername(), userService.getPassword()) != null;
    }

    @Override
    public boolean userIsRegistered(LoginUserService loginUserService) {
         return this.userRepository.findByUsernameAndPassword(loginUserService.getUsername(),
                loginUserService.getPassword()) != null;
    }


}

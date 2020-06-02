package javaweb.workshop.service.impl;

import javaweb.workshop.domain.entity.User;
import javaweb.workshop.domain.servicemodel.LoginUserServiceModel;
import javaweb.workshop.domain.servicemodel.SetUserServiceModel;
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
    public void seedUser(SetUserServiceModel setUserServiceModel) {

        setUserServiceModel.setRole(
                this.roleService.getRole(this.userRepository.count() == 0? "ADMIN" : "USER")
        );

        this.userRepository.saveAndFlush(this.mapper.map(setUserServiceModel, User.class));

    }

    @Override
    public boolean userExists(SetUserServiceModel setUserServiceModel) {
        return this.userRepository.findByUsernameAndPassword(
                setUserServiceModel.getUsername(), setUserServiceModel.getPassword()
        ) != null;
    }

    @Override
    public boolean userExists(LoginUserServiceModel loginUserServiceModel) {
        return this.userRepository.findByUsernameAndPassword(
                loginUserServiceModel.getUsername(), loginUserServiceModel.getPassword()
        ) != null;
    }
}

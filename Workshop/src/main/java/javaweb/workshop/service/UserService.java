package javaweb.workshop.service;

import javaweb.workshop.domain.service.LoginUserService;
import javaweb.workshop.domain.service.SetUserService;

public interface UserService {

    void seedUser(SetUserService userService);

    boolean userExists(SetUserService userService);

    boolean userIsRegistered(LoginUserService loginUserService);



}

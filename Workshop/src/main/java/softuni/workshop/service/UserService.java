package softuni.workshop.service;

import softuni.workshop.domain.service.LoginUserService;
import softuni.workshop.domain.service.SetUserService;

public interface UserService {

    void seedUser(SetUserService userService);

    boolean exists(LoginUserService loginUserService);

}

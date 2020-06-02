package javaweb.workshop.service;

import javaweb.workshop.domain.servicemodel.LoginUserServiceModel;
import javaweb.workshop.domain.servicemodel.SetUserServiceModel;

public interface UserService {

    void seedUser(SetUserServiceModel setUserServiceModel);

    boolean userExists(SetUserServiceModel setUserServiceModel);

    boolean userExists(LoginUserServiceModel loginUserServiceModel);

}

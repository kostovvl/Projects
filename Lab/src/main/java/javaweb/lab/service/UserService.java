package javaweb.lab.service;

import javaweb.lab.domain.dto.userdto.UserAddDto;
import javaweb.lab.domain.dto.userdto.UserRemoveDto;
import javaweb.lab.domain.dto.userdto.UserUpdateDto;
import javaweb.lab.exception.EntityDoesNotExistsException;
import javaweb.lab.exception.EntityExistsException;
import javaweb.lab.exception.EntityInvalidException;

public interface UserService {

    void addUser(UserAddDto userAddDto) throws EntityExistsException;
    void removeUser(UserRemoveDto userRemoveDto) throws EntityDoesNotExistsException;
    void updateUser(UserUpdateDto userUpdateDto) throws EntityDoesNotExistsException, EntityInvalidException;
    long getCount();

}

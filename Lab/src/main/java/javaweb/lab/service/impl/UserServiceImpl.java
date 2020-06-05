package javaweb.lab.service.impl;

import javaweb.lab.domain.dto.UserRoleDto;
import javaweb.lab.domain.dto.userdto.UserAddDto;
import javaweb.lab.domain.dto.userdto.UserRemoveDto;
import javaweb.lab.domain.dto.userdto.UserUpdateDto;
import javaweb.lab.domain.entity.User;
import javaweb.lab.domain.entity.UserRole;
import javaweb.lab.exception.EntityDoesNotExistsException;
import javaweb.lab.exception.EntityExistsException;
import javaweb.lab.exception.EntityInvalidException;
import javaweb.lab.repository.UserRepository;
import javaweb.lab.service.UserRoleService;
import javaweb.lab.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.mapper = mapper;
    }


    @Transactional
    @Override
    public void addUser(UserAddDto userAddDto) throws EntityExistsException {

        if (findByUsername(userAddDto.getUsername()) != null) {
            throw new EntityExistsException();
        } else {


            if (this.userRepository.count() == 0) {
                UserRoleDto admin = this.userRoleService.getRole("ADMIN");
                userAddDto.setRole(admin);
            } else {
                UserRoleDto seller = this.userRoleService.getRole("SELLER");
                userAddDto.setRole(seller);
            }

            User newUser = this.mapper.map(userAddDto, User.class);
            newUser.setActive(true);
            newUser.setCreated(new Date());
            this.userRepository.saveAndFlush(newUser);

        }

    }

    @Override
    public void removeUser(UserRemoveDto userRemoveDto) throws EntityDoesNotExistsException {

        if (findByUsername(userRemoveDto.getUsername()) == null) {
            throw new EntityDoesNotExistsException();
        }

        User forDeletion = findByUsername(userRemoveDto.getUsername());
        this.userRepository.deleteById(forDeletion.getId());

    }

    @Override
    public void updateUser(UserUpdateDto userUpdateDto) throws EntityInvalidException {

        if (findByUsername(userUpdateDto.getUsername()) == null) {
            throw new EntityInvalidException();
        }

        User toBeUpdated = findByUsername(userUpdateDto.getUsername());
        toBeUpdated.setFirstName(userUpdateDto.getFirstName());
        toBeUpdated.setLastName(userUpdateDto.getLastName());
        toBeUpdated.setRole(userUpdateDto.getRole());
        toBeUpdated.setImageUrl(userUpdateDto.getImageUrl());
        toBeUpdated.setModified(new Date());

        this.userRepository.saveAndFlush(toBeUpdated);

    }

    @Override
    public long getCount() {
        return this.userRepository.count();
    }



    private User findByUsername(String username) {
       return this.userRepository.findByUsername(username).orElse(null);
    }




}

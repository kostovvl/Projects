package javaweb.lab.service.impl;

import javaweb.lab.domain.dto.UserRoleDto;
import javaweb.lab.domain.entity.Role;
import javaweb.lab.domain.entity.UserRole;
import javaweb.lab.repository.UserRoleRepository;
import javaweb.lab.service.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper mapper) {
        this.userRoleRepository = userRoleRepository;
        this.mapper = mapper;
    }

    @PostConstruct()
    public void init() {
        if (this.userRoleRepository.count() == 0) {
            UserRole admin = new UserRole("ADMIN");
            UserRole user = new UserRole("USER");
            UserRole seller = new UserRole("SELLER");

            this.userRoleRepository.saveAndFlush(admin);
            this.userRoleRepository.saveAndFlush(user);
            this.userRoleRepository.saveAndFlush(seller);
        }
    }

    @Override
    public UserRoleDto getRole(String role) {
        return this.mapper.map(this.userRoleRepository.findByRole(Role.valueOf(role)), UserRoleDto.class);
    }
}

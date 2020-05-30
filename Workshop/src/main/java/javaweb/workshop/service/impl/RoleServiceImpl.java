package javaweb.workshop.service.impl;

import javaweb.workshop.domain.entity.Role;
import javaweb.workshop.domain.service.SetRoleService;
import javaweb.workshop.repository.RoleRepository;
import javaweb.workshop.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() {
        if (this.roleRepository.count() == 0) {
            Role Admin = new Role("ADMIN");
            Role User = new Role("USER");

            this.roleRepository.saveAndFlush(Admin);
            this.roleRepository.saveAndFlush(User);
        }
    }

    @Override
    public SetRoleService setRole(String name) {

        Role role = this.roleRepository.findByName(name);
        return this.mapper.map(role, SetRoleService.class);
    }
}


package javaweb.workshop.service.impl;

import javaweb.workshop.domain.dto.RoleDto;
import javaweb.workshop.domain.entity.Role;
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
            Role admin = new Role("ADMIN");
            Role user = new Role("USER");

            this.roleRepository.saveAndFlush(admin);
            this.roleRepository.saveAndFlush(user);
        }
    }

    @Override
    public RoleDto extractRole(String roleName) {
        return this.mapper.map(this.roleRepository.findByName(roleName), RoleDto.class);
    }
}

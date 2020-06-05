package javaweb.lab.repository;

import javaweb.lab.domain.entity.Role;
import javaweb.lab.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    UserRole findByRole(Role role);

}

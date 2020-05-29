package softuni.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.workshop.domain.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(String name);

}

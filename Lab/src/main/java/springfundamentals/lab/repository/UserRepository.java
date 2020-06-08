package springfundamentals.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springfundamentals.lab.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

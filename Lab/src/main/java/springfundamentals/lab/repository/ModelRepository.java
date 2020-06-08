package springfundamentals.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springfundamentals.lab.domain.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}

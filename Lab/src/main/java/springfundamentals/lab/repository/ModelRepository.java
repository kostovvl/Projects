package springfundamentals.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springfundamentals.lab.domain.entity.Model;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findAll();

    Model findByName(String name);

}

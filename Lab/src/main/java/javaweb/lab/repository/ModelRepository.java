package javaweb.lab.repository;

import javaweb.lab.domain.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {

    Model findByName(String name);

    @Query("select m from Model as m order by m.brand.name, m.name")
    List<Model> selectAllModels();

}

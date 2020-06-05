package javaweb.lab.repository;

import javaweb.lab.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

    Brand findByName(String name);

}

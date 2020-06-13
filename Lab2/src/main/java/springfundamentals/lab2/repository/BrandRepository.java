package springfundamentals.lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springfundamentals.lab2.domain.entity.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findAll();

    Brand findByName(String name);

}

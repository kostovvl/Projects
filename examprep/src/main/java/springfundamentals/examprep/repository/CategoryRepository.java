package springfundamentals.examprep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springfundamentals.examprep.domain.entity.Category;
import springfundamentals.examprep.domain.entity.CategoryName;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(CategoryName name);

}

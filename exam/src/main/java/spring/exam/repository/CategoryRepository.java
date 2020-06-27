package spring.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.exam.domain.entity.Category;
import spring.exam.domain.entity.CategoryName;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Category findByName(CategoryName name);

}

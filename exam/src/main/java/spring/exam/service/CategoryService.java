package spring.exam.service;

import spring.exam.domain.dto.CategoryDto;
import spring.exam.domain.entity.CategoryName;

public interface CategoryService {

    void initializeCategories();
    CategoryDto findByName(CategoryName name);

}

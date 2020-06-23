package springfundamentals.examprep.service.impl;

import org.springframework.stereotype.Service;
import springfundamentals.examprep.domain.entity.Category;
import springfundamentals.examprep.domain.entity.CategoryName;
import springfundamentals.examprep.repository.CategoryRepository;
import springfundamentals.examprep.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initializeCategories() {
        if (this.categoryRepository.count() != 0) {
            return;
        }
        Arrays.stream(CategoryName.values())
                .forEach(categoryName -> {
                    Category category = new Category(categoryName);
                    category.setDescription(String.format("Description of %s", categoryName.name()));
                    this.categoryRepository.saveAndFlush(category);
                });
    }
}

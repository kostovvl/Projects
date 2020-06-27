package spring.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.exam.domain.dto.CategoryDto;
import spring.exam.domain.entity.Category;
import spring.exam.domain.entity.CategoryName;
import spring.exam.repository.CategoryRepository;
import spring.exam.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public void initializeCategories() {
        if (this.categoryRepository.count() != 0) {
         return;
        }

        Arrays.stream(CategoryName.values())
                .forEach(value -> {
                    Category category = new Category(value);
                    category.setDescription(String.format("Description of %s.",
                            value.name()));
                    this.categoryRepository.saveAndFlush(category);
                });
    }

    @Override
    public CategoryDto findByName(CategoryName name) {

        return this.mapper.map(categoryRepository.findByName(name), CategoryDto.class);
    }
}

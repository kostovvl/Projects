package spring.exam.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.exam.service.CategoryService;

@Component
public class Initialization implements CommandLineRunner {

    private final CategoryService categoryService;

    @Autowired
    public Initialization(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.categoryService.initializeCategories();

    }
}

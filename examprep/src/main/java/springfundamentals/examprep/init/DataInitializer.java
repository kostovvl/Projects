package springfundamentals.examprep.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springfundamentals.examprep.service.CategoryService;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryService categoryService;

    @Autowired
    public DataInitializer(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initializeCategories();
    }
}

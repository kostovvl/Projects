package springfundamentals.examprep.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springfundamentals.examprep.service.CategoryService;

@Component
public class InitializeCategories implements CommandLineRunner {

    private final CategoryService categoryService;

    public InitializeCategories(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initializeCategories();
    }
}

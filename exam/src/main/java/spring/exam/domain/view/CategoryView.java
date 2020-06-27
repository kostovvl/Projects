package spring.exam.domain.view;

import spring.exam.domain.entity.CategoryName;

public class CategoryView extends BaseView {

    private CategoryName name;
    private String description;

    public CategoryView() {
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package springfundamentals.examprep.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryName categoryName;
    private String description;

    public Category() {
    }

    public Category(String name) {
        this.categoryName = CategoryName.valueOf(name);
    }

    @Enumerated(EnumType.STRING)
    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    @Column(name = "description", columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

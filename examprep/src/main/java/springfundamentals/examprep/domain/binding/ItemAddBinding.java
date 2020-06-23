package springfundamentals.examprep.domain.binding;

import org.hibernate.validator.constraints.Length;
import springfundamentals.examprep.domain.entity.CategoryName;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class ItemAddBinding {

    private String name;
    private String description;
    private CategoryName category;
    private String gender;
    private BigDecimal price;

    public ItemAddBinding() {
    }

    @Length(min = 2, message = "Name must be at least 2 characters long")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 2, message = "Description must be at least 2 characters long")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @DecimalMin(value = "0", message = "Enter valid price!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

package springfundamentals.lab2.domain.binding;

import springfundamentals.lab2.domain.entity.Brand;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ModelBinding {

    private String name;
    private String imageUrl;
    private int startYear;
    private int endYear;
    private String brand;

    public ModelBinding() {
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @NotNull
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

package springfundamentals.lab.domain.dto;

import springfundamentals.lab.domain.entity.Brand;

import java.util.Date;

public class ModelGetDto extends BaseDto {

    private String name;
    private String imageUrl;
    private int startYear;
    private int endYear;
    private Date created;
    private Date modified;
    private BrandGetDto brand;

    public ModelGetDto() {
    }

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public BrandGetDto getBrand() {
        return brand;
    }

    public void setBrand(BrandGetDto brand) {
        this.brand = brand;
    }
}

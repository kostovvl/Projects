package springfundamentals.lab.domain.dto;

public class BrandSetDto extends BaseDto{
    
    private String name;

    public BrandSetDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

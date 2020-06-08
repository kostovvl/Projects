package springfundamentals.lab.domain.dto;

import java.util.Date;

public class BrandGetDto extends BaseDto {

    private String name;
    private Date created;
    private Date modified;

    public BrandGetDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

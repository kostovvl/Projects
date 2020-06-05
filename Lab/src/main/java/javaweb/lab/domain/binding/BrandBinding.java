package javaweb.lab.domain.binding;

import javax.validation.constraints.NotNull;

public class BrandBinding {

    private String name;

    public BrandBinding() {
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

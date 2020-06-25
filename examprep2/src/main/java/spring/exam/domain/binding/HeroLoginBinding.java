package spring.exam.domain.binding;

import org.hibernate.validator.constraints.Length;
import spring.exam.domain.entity.HeroClass;

import javax.validation.constraints.Min;

public class HeroLoginBinding extends BaseBinding {

    private String name;
    private HeroClass heroClass;
    private int level;

    public HeroLoginBinding() {
    }

    @Length(min = 2, message = "Hero name must be at least 2 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    @Min(value = 0, message = "Level must be positive number!")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

package spring.exam.domain.dto;

import spring.exam.domain.entity.HeroClass;

public class HeroDto extends BaseDto {

    private String name;
    private HeroClass HeroClass;
    private int level;

    public HeroDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public spring.exam.domain.entity.HeroClass getHeroClass() {
        return HeroClass;
    }

    public void setHeroClass(spring.exam.domain.entity.HeroClass heroClass) {
        HeroClass = heroClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

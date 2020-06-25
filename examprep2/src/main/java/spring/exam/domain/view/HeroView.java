package spring.exam.domain.view;

import spring.exam.domain.entity.HeroClass;

public class HeroView extends BaseView {

    private String name;
    private HeroClass heroClass;
    private int level;
    private String image;

    public HeroView() {
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

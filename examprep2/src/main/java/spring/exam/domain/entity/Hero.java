package spring.exam.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {

    private String name;
    private HeroClass HeroClass;
    private int level;

    public Hero() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public HeroClass getHeroClass() {
        return HeroClass;
    }

    public void setHeroClass(HeroClass heroClass) {
        HeroClass = heroClass;
    }

    @Column(name = "level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

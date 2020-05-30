package javaweb.workshop.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "exercises")
public class Exercise extends BaseEntity {

    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;
    private Set<Homework> homeWorks;

    public Exercise() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "started_on")
    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    @Column(name = "due_date")
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @OneToMany(mappedBy = "exercise", targetEntity = Homework.class,
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Homework> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(Set<Homework> homeWorks) {
        this.homeWorks = homeWorks;
    }
}

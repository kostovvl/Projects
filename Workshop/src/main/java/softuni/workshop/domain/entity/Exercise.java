package softuni.workshop.domain.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "exercises")
public class Exercise extends BaseEntity {

    private String name;
    private LocalDate startedOn;
    private LocalDate dueDate;
    private Set<Homework> homeWorks;


    public Exercise() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "started_on")
    public LocalDate getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDate startedOn) {
        this.startedOn = startedOn;
    }


    @Column(name = "due_date")
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
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

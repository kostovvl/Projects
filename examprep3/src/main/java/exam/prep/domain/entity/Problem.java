package exam.prep.domain.entity;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "problems")
public class Problem extends BaseEntity {

    private String name;
    private int points;
    private Set<Submission> submissions;

    public Problem() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "points")
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @OneToMany(mappedBy = "problem", targetEntity = Submission.class,
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }
}

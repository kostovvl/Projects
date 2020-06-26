package exam.prep.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "submissions")
public class Submission extends BaseEntity {

    private String code;
    private int achievedResult;
    private Date createdOn;
    private Problem problem;
    private User user;

    public Submission() {
    }

    @Column(name = "code", columnDefinition = "text")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "result")
    public int getAchievedResult() {
        return achievedResult;
    }

    public void setAchievedResult(int achievedResult) {
        this.achievedResult = achievedResult;
    }

    @Column(name = "created_on")
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @ManyToOne()
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    @ManyToOne()
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

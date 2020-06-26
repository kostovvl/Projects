package exam.prep.domain.binding;

import exam.prep.domain.entity.Problem;
import exam.prep.domain.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Random;

public class SubmissionCreateBinding extends BaseBinding {

    private String code;
    private int achievedResult;
    private Date createdOn;
    private Problem problem;
    private User user;


    public SubmissionCreateBinding() {

    }

    @Length(min = 1, message = "Code field can not be empty!")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAchievedResult() {
        return achievedResult;
    }

    public void setAchievedResult(int achievedResult) {
        this.achievedResult = achievedResult;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

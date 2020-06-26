package exam.prep.domain.dto;

import exam.prep.domain.entity.Problem;
import exam.prep.domain.entity.User;

import java.util.Date;

public class SubmissionDto extends BaseDto {

    private String code;
    private int achievedResult;
    private Date createdOn;
    private Problem problem;
    private User user;

    public SubmissionDto() {



    }

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

package exam.prep.domain.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;

public class ProblemCreateBinding extends BaseBinding {

    private String name;
    private int totalPoints;

    public ProblemCreateBinding() {
    }

    @Length(min = 1, message = "Name can not be empty!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(value = 0, message = "Points can not be negative number!")
    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}

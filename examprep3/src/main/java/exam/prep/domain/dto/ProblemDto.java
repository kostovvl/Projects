package exam.prep.domain.dto;

public class ProblemDto extends BaseDto {

    private String name;
    private int points;

    public ProblemDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

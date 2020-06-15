package javaweb.workshop.domain.binding;

import javax.validation.constraints.Pattern;

public class AddHomeworkBinding {

    private String exercise;
    private String gitAddress;

    public AddHomeworkBinding() {
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+\\/.+?", message = "Invalid github address")
    public String getGitAddress() {
        return gitAddress;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }
}

package javaweb.workshop.domain.dto;

import javaweb.workshop.domain.entity.Exercise;
import javaweb.workshop.domain.entity.User;

import java.time.LocalDateTime;

public class HomeworkDto extends BaseDto {

    private LocalDateTime addedOn;
    private String gitAddress;
    private UserDto author;
    private ExerciseDto exercise;

    public HomeworkDto() {
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }

    public ExerciseDto getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseDto exercise) {
        this.exercise = exercise;
    }
}

package javaweb.workshop.domain.dto;

import java.time.LocalDateTime;

public class ExerciseDto extends BaseDto{

    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;

    public ExerciseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}

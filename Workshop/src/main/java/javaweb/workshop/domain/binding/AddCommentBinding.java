package javaweb.workshop.domain.binding;


import javaweb.workshop.domain.dto.UserDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AddCommentBinding {

    private String score;
    private String textContent;
    private String authorId;
    private String homeworkId;

    public AddCommentBinding() {
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Length(min = 5, message = "Please enter Comment with at least 5 characters!")
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }
}

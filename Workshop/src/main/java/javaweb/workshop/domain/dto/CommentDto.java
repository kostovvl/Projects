package javaweb.workshop.domain.dto;

public class CommentDto extends BaseDto {

    private String score;
    private String textContent;
    private UserDto authorId;
    private HomeworkDto homeworkId;

    public CommentDto() {
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public UserDto getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UserDto authorId) {
        this.authorId = authorId;
    }

    public HomeworkDto getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(HomeworkDto homeworkId) {
        this.homeworkId = homeworkId;
    }
}

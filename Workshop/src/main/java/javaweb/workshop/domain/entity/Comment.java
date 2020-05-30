package javaweb.workshop.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    private String score;
    private String textContent;
    private User author;
    private Homework homework;

    public Comment() {
    }

    @Column(name = "score")
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    @Column(name = "text_content", columnDefinition = "text")
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @ManyToOne()
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne()
    @JoinColumn(name = "homework_id", referencedColumnName = "id")
    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }
}

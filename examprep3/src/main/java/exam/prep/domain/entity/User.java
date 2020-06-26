package exam.prep.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private Set<Submission> submissions;

    public User() {
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "user", targetEntity = Submission.class,
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }
}

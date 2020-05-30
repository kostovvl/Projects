package javaweb.workshop.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String git;
    private Role role;
    private Set<Homework> homeWorks;
    private Set<Comment> comments;

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

    @Column(name = "git_hub")
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "author", targetEntity = Homework.class,
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Homework> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(Set<Homework> homeWorks) {
        this.homeWorks = homeWorks;
    }

    @OneToMany(mappedBy = "author", targetEntity = Comment.class,
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}

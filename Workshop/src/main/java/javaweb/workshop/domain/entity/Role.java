package javaweb.workshop.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role  extends BaseEntity {

    private String name;
    private Set<User> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role", targetEntity = User.class,
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

package javaweb.lab.domain.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity {


    private Role role;
    private Set<User> users;

    public UserRole() {
    }

    public UserRole(String role) {
        this.role = Role.valueOf(role);
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

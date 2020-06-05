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

    @ManyToMany()
    @JoinTable(name = "user_roles_users",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"))
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

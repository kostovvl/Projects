package javaweb.lab.domain.dto.userdto;

import javaweb.lab.domain.dto.BaseDto;
import javaweb.lab.domain.entity.UserRole;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class UserAddDto extends BaseDto {

    private String username;
    private String firstName;
    private String lastName;
    private boolean active;
    private Set<UserRole> role;
    private String imageUrl;
    private Date created;
    private Date modified;

    public UserAddDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<UserRole> getRole() {
        return role;
    }

    public void setRole(Set<UserRole> role) {
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public void addRole(UserRole role) {
        this.role.add(role);
    }
}

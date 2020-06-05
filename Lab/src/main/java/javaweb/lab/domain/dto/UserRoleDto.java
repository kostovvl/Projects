package javaweb.lab.domain.dto;

import javaweb.lab.domain.dto.BaseDto;
import javaweb.lab.domain.entity.Role;

public class UserRoleDto extends BaseDto {

    private Role role;

    public UserRoleDto() {
    }

    public UserRoleDto(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

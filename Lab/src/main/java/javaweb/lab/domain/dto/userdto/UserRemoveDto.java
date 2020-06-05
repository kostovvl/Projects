package javaweb.lab.domain.dto.userdto;

import javaweb.lab.domain.dto.BaseDto;

public class UserRemoveDto extends BaseDto {

    private String username;

    public UserRemoveDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

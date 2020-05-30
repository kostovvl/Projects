package javaweb.workshop.domain.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginUserBinding {

    private String username;
    private String password;

    public LoginUserBinding() {
    }

    @NotNull
    @Length(min = 3, max = 10, message = "Username length must be between 3 and 10 characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Length(min = 3, message = "Password length must be at least 2 characters long!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

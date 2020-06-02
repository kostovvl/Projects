package javaweb.workshop.domain.binding;

import javax.validation.constraints.NotNull;

public class LoginUserBinding {

    private String username;
    private String password;

    public LoginUserBinding() {

    }

    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

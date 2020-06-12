package springfundamentals.lab2.domain.binding;

import io.micrometer.core.lang.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginBinding {

    private String username;
    private String password;

    public LoginBinding() {
    }

    @NonNull
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

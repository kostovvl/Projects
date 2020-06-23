package springfundamentals.examprep.domain.binding;

import org.hibernate.validator.constraints.Length;

public class UserLoginBinding {

    private String username;
    private String password;

    public UserLoginBinding() {
    }

    @Length(min = 2, message = "Username must be more than 2 characters long!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 3, message = "Password must be more than 3 characters long!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

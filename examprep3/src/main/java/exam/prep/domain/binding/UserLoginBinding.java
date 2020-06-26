package exam.prep.domain.binding;

import org.hibernate.validator.constraints.Length;

public class UserLoginBinding extends BaseBinding {

    private String username;
    private String password;

    public UserLoginBinding() {
    }

    @Length(min = 1, message = "Username can not be empty")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 1, message = "Password can not be empty")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

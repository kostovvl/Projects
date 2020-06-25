package spring.exam.domain.binding;

import org.hibernate.validator.constraints.Length;

public class UserLoginBinding extends BaseBinding {

    private String username;
    private String password;

    public UserLoginBinding() {
    }

    @Length(min = 2, message = "Username must be atleast 2 characters long!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 2, message = "Password must be atleast 2 characters long!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

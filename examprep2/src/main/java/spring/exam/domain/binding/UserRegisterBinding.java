package spring.exam.domain.binding;

import org.hibernate.validator.constraints.Length;
import spring.exam.domain.entity.BaseEntity;

import javax.validation.constraints.Email;

public class UserRegisterBinding extends BaseBinding {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String country;

    public UserRegisterBinding() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Length(min = 2, message = "Email must be atleast 2 characters long!")
    @Email()
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 1, message = "Please, enter Country name!")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

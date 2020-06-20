package springfundamentals.examprep.domain.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class UserRegisterBinding {

    private String username;
    private String email;
    private BigDecimal budget;
    private String password;
    private String confirmPassword;

    public UserRegisterBinding() {
    }

    @Length(min = 2, message = "Username length must be more than two characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 1, message =  "Enter valid email address!")
    @Email(message = "Enter valid email address!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @DecimalMin(value = "0", message = "Enter correct value!")
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @Length(min = 3, message = "Password length must be more than two characters!")
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
}

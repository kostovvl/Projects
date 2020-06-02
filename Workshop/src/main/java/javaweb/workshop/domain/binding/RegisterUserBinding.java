package javaweb.workshop.domain.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegisterUserBinding {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String git;

    public RegisterUserBinding() {
    }

    @NotNull
    @Length(min = 2, max = 10, message = "Username must be between 2 and 10 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Length(min = 3, max = 10, message = "Password must be betweeen 3 and 10 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull
  //  @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
   // @Pattern(regexp = "https:\\/github\\.com/.+\\/.+\\/")
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}

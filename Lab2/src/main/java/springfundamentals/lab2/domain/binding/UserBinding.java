package springfundamentals.lab2.domain.binding;


import org.hibernate.validator.constraints.Length;

public class UserBinding {

    private String username;
    private String firstName;
    private String lastName;
    private String password;

    public UserBinding() {
    }

    @Length(min = 2, max = 10, message = "Username is must be Between 2 and 10 symbols.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 2, max = 10, message = "First Name is must be Between 2 and 10 symbols.")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Length(min = 2, max = 10, message = "Last Name is must be Between 2 and 10 symbols.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Length(min = 2, max = 10, message = "Password is must be Between 2 and 10 symbols.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

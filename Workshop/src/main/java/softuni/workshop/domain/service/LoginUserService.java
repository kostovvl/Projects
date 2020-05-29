package softuni.workshop.domain.service;

public class LoginUserService extends BaseService {

    private String username;
    private String password;

    public LoginUserService() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

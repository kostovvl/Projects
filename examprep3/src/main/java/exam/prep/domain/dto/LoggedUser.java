package exam.prep.domain.dto;

public class LoggedUser extends BaseDto {

    private String username;

    public LoggedUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

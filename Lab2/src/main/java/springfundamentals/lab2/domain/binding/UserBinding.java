package springfundamentals.lab2.domain.binding;

import springfundamentals.lab2.domain.entity.Role;

import javax.validation.constraints.NotNull;

public class UserBinding {

    private String username;
    private String firstName;
    private String lastName;
    private String imageUrl;

    public UserBinding() {
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

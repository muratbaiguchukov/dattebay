package kg.itacademy.testproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    private String id;
    private String login;
    private String password;
    private String email;
    private Boolean isActive;
}
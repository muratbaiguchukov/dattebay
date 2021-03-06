package kg.itacademy.testproject.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {

    @Column(name = "user_login", nullable = false)
    private String login;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

}

package kg.itacademy.testproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseModel {
    private Long id;
    private String name;
    private UserModel userId;
}

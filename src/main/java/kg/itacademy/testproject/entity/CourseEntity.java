package kg.itacademy.testproject.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEntity extends BaseEntity {
    @Column(name = "course_name", nullable = false)
    private String name;

    @Column(name = "user_id")
    private UserEntity userId;
}

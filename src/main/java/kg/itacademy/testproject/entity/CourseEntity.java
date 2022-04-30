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
    @Column(name = "course_name")
    private String name;
}

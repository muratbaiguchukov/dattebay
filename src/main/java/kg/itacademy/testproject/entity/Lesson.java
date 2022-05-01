package kg.itacademy.testproject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson extends BaseEntity{

    @Column(name = "lesson_name")
    private String name;

}
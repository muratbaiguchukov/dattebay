package kg.itacademy.testproject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "lessons_files")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonFile extends BaseEntity{

    @ManyToMany
    @JoinColumn(name = "lesson_id", nullable = false)
    Lesson lesson;

    @ManyToMany
    @JoinColumn(name = "file_id", nullable = false)
    File file;
}
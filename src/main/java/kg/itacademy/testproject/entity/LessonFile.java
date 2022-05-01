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

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    File file;
}
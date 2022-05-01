package kg.itacademy.testproject.repository;

import kg.itacademy.testproject.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long>{
    List<Lesson> findAllByName(String lessonName);
}
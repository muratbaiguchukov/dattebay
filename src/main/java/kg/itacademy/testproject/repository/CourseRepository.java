package kg.itacademy.testproject.repository;

import kg.itacademy.testproject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByCourseName(String courseName);
}
package kg.itacademy.testproject.repository;

import kg.itacademy.testproject.entity.CourseEntity;
import kg.itacademy.testproject.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {


}

package kg.itacademy.testproject.repository;

import kg.itacademy.testproject.entity.LessonEntity;
import kg.itacademy.testproject.model.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

}

package kg.itacademy.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonFile extends JpaRepository<LessonFile, Long>{

}
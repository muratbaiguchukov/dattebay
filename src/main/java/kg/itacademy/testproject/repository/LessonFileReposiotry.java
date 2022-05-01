package kg.itacademy.testproject.repository;

import kg.itacademy.testproject.entity.LessonFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonFileReposiotry extends JpaRepository<LessonFile, Long>{

}
package kg.itacademy.testproject.repository;

import kg.itacademy.testproject.entity.Course;
import kg.itacademy.testproject.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long>{
    List<File> findAllByFileName(String fileName);
}
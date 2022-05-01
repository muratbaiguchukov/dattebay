package kg.itacademy.testproject.repository;

import kg.itacademy.testproject.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    @Query(value = "select * from images", nativeQuery = true)
    List<ImageEntity> findAll ();

}

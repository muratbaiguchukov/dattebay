package kg.itacademy.testproject.repository;


import kg.itacademy.testproject.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {

    @Query(value = "select * from videos", nativeQuery = true)
    List<VideoEntity> findAll ();
}

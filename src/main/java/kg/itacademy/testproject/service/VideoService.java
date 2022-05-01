package kg.itacademy.testproject.service;

import kg.itacademy.testproject.entity.VideoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    VideoEntity save ( MultipartFile entityVideos);

    List<VideoEntity> getAllVideos();
}

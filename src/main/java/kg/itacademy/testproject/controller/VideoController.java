package kg.itacademy.testproject.controller;

import kg.itacademy.testproject.entity.VideoEntity;
import kg.itacademy.testproject.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {
    @Autowired
    VideoService videoService;

    @PostMapping
    public VideoEntity saveVideo ( MultipartFile videos )
    {
       return videoService.save ( videos );
    }

    @GetMapping
    public List<VideoEntity> getAllVideos ()
    {
        return videoService.getAllVideos ();
    }
}

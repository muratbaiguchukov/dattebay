package kg.itacademy.testproject.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kg.itacademy.testproject.entity.VideoEntity;
import kg.itacademy.testproject.repository.VideoRepository;
import kg.itacademy.testproject.service.VideoService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.http.entity.mime.MultipartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoServiceImpl implements VideoService {

    static final String CLOUDINARY_URL = "cloudinary://953623852329234:f2WCsbx2_rVeTfRbaTHM67CAw-A@dattebayo";
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public VideoEntity save ( MultipartFile entityVideos )
    {
        File file;
        try
        {
            file = Files.createTempFile ( System.currentTimeMillis () + "", entityVideos.getOriginalFilename ().
                    substring ( entityVideos.getOriginalFilename ().length () - 4 ) ).toFile ();
            entityVideos.transferTo ( file );

            Cloudinary cloudinary = new Cloudinary ( CLOUDINARY_URL );
            Map uploadResult = cloudinary.uploader ().upload ( file, ObjectUtils.emptyMap () );

            VideoEntity videos = VideoEntity.builder ()
                    .videoName ( entityVideos.getName () ).videoUrl ( ( String ) uploadResult.get ( "url" ) ).build ();

            return videoRepository.save ( videos );
        } catch (IOException e)
        {
            throw new RuntimeException ( e );
        }
    }

    @Override
    public List<VideoEntity> getAllVideos ()
    {
        return videoRepository.findAll ();
    }
}

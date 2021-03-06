package kg.itacademy.testproject.controller;

import kg.itacademy.testproject.exceptions.LessonNotFoundException;
import kg.itacademy.testproject.model.CourseModel;
import kg.itacademy.testproject.model.LessonModel;
import kg.itacademy.testproject.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/courses")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    //Добавить курс
    @PostMapping(path = "/create")
    public ResponseEntity<CourseModel> addCourse ( @RequestBody CourseModel courseModels )
    {
        CourseModel createdCourse = courseService.addCourse ( courseModels );
        if ( createdCourse.getId () != null )
        {
            return ResponseEntity.status ( HttpStatus.CREATED ).body ( createdCourse );
        } else
        {
            return ResponseEntity
                    .status ( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body ( null );
        }
    }

    //Удалить курс по id
    @DeleteMapping(path = "/delete/{courseId}")
    public ResponseEntity<Boolean> deleteCoureById ( @PathVariable("courseId") Long courseId )
    {
        try
        {
            return ResponseEntity.ok ( courseService.deleteCourseById ( courseId ) );
        } catch (LessonNotFoundException e)
        {
            log.error ( e.getMessage (), e );
            return ResponseEntity
                    .status ( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body ( null );
        }
    }

}

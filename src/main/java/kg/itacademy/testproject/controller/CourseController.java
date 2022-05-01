package kg.itacademy.testproject.controller;

import kg.itacademy.testproject.exceptions.LessonNotFoundException;
import kg.itacademy.testproject.model.CourseModel;
import kg.itacademy.testproject.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping(path = "/api/courses")
//@Slf4j
//public class CourseController {
//
//    @Autowired
//    private CourseService courseService;
//
//    //Добавить курс
//    @PostMapping(path = "/create")
//    public ResponseEntity<CourseModel> addCourse (@RequestBody CourseModel courseModels )
//    {
//        CourseModel createdCourse = courseService.addCourse ( courseModels );
//        if ( createdCourse.getId () != null )
//        {
//            return ResponseEntity.status ( HttpStatus.CREATED ).body ( createdCourse );
//        } else
//        {
//            return ResponseEntity
//                    .status ( HttpStatus.INTERNAL_SERVER_ERROR )
//                    .body ( null );
//        }
//    }
//
//    //Удалить курс по id
//    @DeleteMapping(path = "/delete/{courseId}")
//    public ResponseEntity<Boolean> deleteCourseById ( @PathVariable("courseId") Long courseId )
//    {
//        try
//        {
//            return ResponseEntity.ok ( courseService.deleteCourseById ( courseId ) );
//        } catch (LessonNotFoundException e)
//        {
//            log.error ( e.getMessage (), e );
//            return ResponseEntity
//                    .status ( HttpStatus.INTERNAL_SERVER_ERROR )
//                    .body ( null );
//        }
//    }
//}

@RestController
@RequestMapping(path = "/api/course")
@Slf4j
public class CourseController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CourseService courseService;

    @PostMapping(path = "/create")
    public ResponseEntity<CourseModel> createNewCourse(@RequestBody CourseModel courseModel) {
        CourseModel result = courseService.create(courseModel);
        //Если при создании поле id что то есть, значит мы создали успешно и можем вернуть 201
        if (result.getId() != null) {
            //вернется со статус 201
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            //вернется со статус 500 если поле id пустое
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Boolean> updateCourse(@RequestBody CourseModel courseModel) {
        try {
            courseService.update(courseModel);
            return ResponseEntity.ok(true);
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<CourseModel> getCourseById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(courseService.getById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getByCourseName")
    public ResponseEntity<List<CourseModel>> getByCourseName(@RequestParam("courseName") String courseName) {
        try {
            return ResponseEntity.ok(courseService.getAllByCourseName(courseName));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(courseService.deleteById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}

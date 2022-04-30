package kg.itacademy.testproject.controller;

import kg.itacademy.testproject.exceptions.LessonNotFoundException;
import kg.itacademy.testproject.model.LessonModel;
import kg.itacademy.testproject.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@ResponseBody
@RequestMapping(path = "/api/lessons")
@Slf4j
public class LessonController {

    @Autowired
    private LessonService lessonService;

    //Добавить урок
    @PostMapping(path = "/create")
    public ResponseEntity<LessonModel> addLesson ( @RequestBody LessonModel lessonModels )
    {
        LessonModel createdLesson = lessonService.addLesson ( lessonModels );
        if ( createdLesson.getId () != null )
        {
            return ResponseEntity.status ( HttpStatus.CREATED ).body ( createdLesson );
        } else
        {
            return ResponseEntity
                    .status ( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body ( null );
        }
    }

    //Удалить урок по id
    @DeleteMapping(path = "/delete/{lessonId}")
    public ResponseEntity<Boolean> deleteLessonById ( @PathVariable("lessonId") Long lessonId )
    {
        try
        {
            return ResponseEntity.ok ( lessonService.deleteLessonById ( lessonId ) );
        } catch (LessonNotFoundException e)
        {
            log.error ( e.getMessage (), e );
            return ResponseEntity
                    .status ( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body ( null );
        }
    }
}

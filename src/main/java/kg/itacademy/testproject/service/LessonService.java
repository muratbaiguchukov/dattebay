package kg.itacademy.testproject.service;

import kg.itacademy.testproject.model.LessonModel;
import kg.itacademy.testproject.model.UserModel;

import java.util.List;

public interface LessonService {
    LessonModel addLesson ( LessonModel lesson );

    Boolean deleteLessonById ( Long lessonId );

    List<LessonModel> getAllLessons ();

    LessonModel getLessonById ( Long lessonId );
}

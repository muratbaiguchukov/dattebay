package kg.itacademy.testproject.service;

import kg.itacademy.testproject.model.CourseModel;
import kg.itacademy.testproject.model.LessonModel;

import java.util.List;

public interface LessonService {
    LessonModel create(LessonModel lessonModel);

    boolean update(LessonModel lessonModel);

    boolean deleteById(Long id);

    LessonModel getById(Long id);

    List<LessonModel> getAllByLessonName(String lessonName);
}

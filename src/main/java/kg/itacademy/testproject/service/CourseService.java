package kg.itacademy.testproject.service;

import kg.itacademy.testproject.model.CourseModel;

import java.util.List;

public interface CourseService {

    CourseModel addCourse ( CourseModel course );

    Boolean deleteCourseById ( Long courseId );

    List<CourseModel> getAllCourses ();
}

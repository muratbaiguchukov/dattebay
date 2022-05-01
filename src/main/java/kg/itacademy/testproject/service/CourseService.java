package kg.itacademy.testproject.service;

import kg.itacademy.testproject.model.CourseModel;

import java.util.List;

public interface CourseService {

//    CourseModel addCourse (CourseModel course );
//
//    Boolean deleteCourseById ( Long courseId );
//
//    List<CourseModel> getAllCourses ();
//}

    CourseModel create(CourseModel courseModel);

    boolean update(CourseModel courseModel);

    boolean deleteById(Long id);

    CourseModel getById(Long id);

    List<CourseModel> getAllByName(String courseName);

}
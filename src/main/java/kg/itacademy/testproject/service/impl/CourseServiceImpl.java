package kg.itacademy.testproject.service.impl;

import kg.itacademy.testproject.entity.CourseEntity;
import kg.itacademy.testproject.entity.LessonEntity;
import kg.itacademy.testproject.model.CourseModel;
import kg.itacademy.testproject.model.LessonModel;
import kg.itacademy.testproject.repository.CourseRepository;
import kg.itacademy.testproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    //Добавить курс
    @Override
    public CourseModel addCourse ( CourseModel course )
    {
        return null;
    }

    //Удалить курс по id
    @Override
    public Boolean deleteCourseById ( Long courseId )
    {
        return null;
    }

    //Вытащить все курсы
    @Override
    public List<CourseModel> getAllCourses ()
    {
        List<CourseEntity> courseEntityList = courseRepository.findAll ();

        List<CourseModel> courseModelList = new ArrayList<> ();

        for (CourseEntity course : courseEntityList)
        {
            CourseModel courseModel = new CourseModel ();
            courseModel.setName ( courseModel.getName () );

            courseModelList.add ( courseModel );
        }

        return courseModelList;
    }
}

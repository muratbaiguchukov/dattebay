package kg.itacademy.testproject.service.impl;

import kg.itacademy.testproject.entity.LessonEntity;
import kg.itacademy.testproject.model.LessonModel;
import kg.itacademy.testproject.model.UserModel;
import kg.itacademy.testproject.repository.LessonRepository;
import kg.itacademy.testproject.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    //Добавить урок
    @Override
    public LessonModel addLesson ( LessonModel lesson )
    {
        return null;
    }

    //Удалить урок по id
    @Override
    public Boolean deleteLessonById ( Long lessonId )
    {
        return null;
    }

    //Вытащить все уроки
    @Override
    public List<LessonModel> getAllLessons ()
    {
        List<LessonEntity> lessonEntityList = lessonRepository.findAll ();

        List<LessonModel> lessonModelList = new ArrayList<> ();

        for (LessonEntity lesson : lessonEntityList)
        {
            LessonModel lessonModel = new LessonModel ();
            lessonModel.setName ( lesson.getName () );

            lessonModelList.add ( lessonModel );
        }

        return lessonModelList;
    }

    @Override
    public LessonModel getLessonById ( Long lessonId )
    {
        return null;
    }
}
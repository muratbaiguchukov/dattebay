package kg.itacademy.testproject.service.impl;

import kg.itacademy.testproject.entity.Course;
import kg.itacademy.testproject.entity.Lesson;
import kg.itacademy.testproject.exceptions.CourseModelNullException;
import kg.itacademy.testproject.exceptions.CourseNotFoundException;
import kg.itacademy.testproject.exceptions.LessonNotFoundException;
import kg.itacademy.testproject.model.CourseModel;
import kg.itacademy.testproject.model.LessonModel;
import kg.itacademy.testproject.repository.CourseRepository;
import kg.itacademy.testproject.repository.LessonRepository;
import kg.itacademy.testproject.service.CourseService;
import kg.itacademy.testproject.service.LessonService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public LessonModel create(LessonModel lessonModel) {

        //Валидация
        if (lessonModel == null) {
            throw new RuntimeException ("Create lesson model is null");
        } else if (Strings.isBlank(lessonModel. getName())) {
            throw new InvalidParameterException("lesson name can't be blank");
        }

        //Маппинг
        Lesson lesson = new Lesson();
        lesson.setName(lessonModel.getName());
        lesson = lessonRepository.save(lesson);

        // Обратный маппинг
        lessonModel.setId(lesson.getId());

        //Вернуть результат
        return lessonModel;
    }

    @Override
    public boolean update(LessonModel lessonModel) {
        //Валидация
        if (lessonModel == null) {
            throw new RuntimeException("Create lesson model is null");
        } else if (lessonModel.getName() == null || lessonModel.getName().equals("")) {
            throw new InvalidParameterException("lesson name can't be empty");
        } else if (lessonModel.getId() == null) {
            throw new InvalidParameterException("lesson id can't be null");
        }
        //Проверка на то что есть такой lesson с таким id
        Lesson existLesson = lessonRepository.getById(lessonModel.getId());
        if (existLesson == null) {
            throw new LessonNotFoundException("lesson not found by id " + lessonModel.getId());
        }

        //маппим
        existLesson.setName(lessonModel.getName());

        //обновляем
        existLesson = lessonRepository.save(existLesson);

        //Если id не нулл после обновляения то можем считать что update прошел успешно в бд.
        return existLesson.getId() != null;
    }

    @Override
    public boolean deleteById(Long id) {
        //Валидация
        if (!lessonRepository.existsById(id)) {
            throw new LessonNotFoundException("Lesson not found by id: " + id);
        }

        //Удаление
        lessonRepository.deleteById(id);

        //Вернется тру если никаких исключений не произошло.
        return true;
    }

    @Override
    public LessonModel getById(Long id) {
        //Валидация
        if (id == null) {
            throw new InvalidParameterException("Id is null");
        }

        //Ищем в бд с таким айди
        Lesson existEntity = lessonRepository.getById(id);

        //Если в бд нет такого айди то вернет null. Значит мы должны выбросить исключение о том что не нашли такой lesson
        if (existEntity == null) {
            throw new LessonNotFoundException("lesson not found by id: " + id);
        }

        //Маппинг
        LessonModel existModel = new LessonModel();
        existModel.setId(existEntity.getId());
        existModel.setName(existEntity.getName());

        return existModel;
    }

    @Override
    public List<LessonModel> getAllByName(String lessonName) {
        //Валидация
        if (Strings.isBlank(lessonName)) {
            throw new InvalidParameterException("lesson name is blank");
        }

        //Достаем все lesson по имени
        List<Lesson> lessonEntityList = lessonRepository.findAllByName(lessonName);

        //Создаем пустой массив моделек
        List<LessonModel> lessonModelList = new ArrayList<>();

        //Проходимся по каждому чтобы смаппить все элементы в модельки
        for (Lesson element : lessonEntityList) {
            //Маппим по одному каждый энтити в модель
            LessonModel lessonModel = new LessonModel();
            lessonModel.setId(element.getId());
            lessonModel.setName(element.getName());

            //добавляем в массив моделек
            lessonModelList.add(lessonModel);
        }

        //Возвращаем модельки
        return lessonModelList;
    }
}


        ;
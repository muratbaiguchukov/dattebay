package kg.itacademy.testproject.service.impl;

import kg.itacademy.testproject.entity.Course;
import kg.itacademy.testproject.exceptions.CourseModelNullException;
import kg.itacademy.testproject.exceptions.CourseNotFoundException;
import kg.itacademy.testproject.model.CourseModel;
import kg.itacademy.testproject.repository.CourseRepository;
import kg.itacademy.testproject.service.CourseService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseModel create(CourseModel courseModel) {

        //Валидация
        if (courseModel == null) {
            throw new CourseModelNullException("Create course model is null");
        } else if (Strings.isBlank(courseModel. getName())) {
            throw new InvalidParameterException("course name can't be blank");
        }

        //Маппинг
        Course course = new Course();
        course.setName(courseModel.getName());
        course = courseRepository.save(course);

        // Обратный маппинг
        courseModel.setId(course.getId());

        //Вернуть результат
        return courseModel;
    }

    @Override
    public boolean update(CourseModel courseModel) {
        //Валидация
        if (courseModel == null) {
            throw new CourseModelNullException("Create course model is null");
        } else if (courseModel.getName() == null || courseModel.getName().equals("")) {
            throw new InvalidParameterException("course name can't be empty");
        } else if (courseModel.getId() == null) {
            throw new InvalidParameterException("course id can't be null");
        }
        //Проверка на то что есть такой course с таким id
        Course existCourse = courseRepository.getById(courseModel.getId());
        if (existCourse == null) {
            throw new CourseNotFoundException("course not found by id " + courseModel.getId());
        }

        //маппим
        existCourse.setName(courseModel.getName());

        //обновляем
        existCourse = courseRepository.save(existCourse);

        //Если id не нулл после обновляения то можем считать что update прошел успешно в бд.
        return existCourse.getId() != null;
    }

    @Override
    public boolean deleteById(Long id) {
        //Валидация
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Course not found by id: " + id);
        }

        //Удаление
        courseRepository.deleteById(id);

        //Вернеться тру если никаких исключений не произошло.
        return true;
    }

    @Override
    public CourseModel getById(Long id) {
        //Валидация
        if (id == null) {
            throw new InvalidParameterException("Id is null");
        }

        //Ищем в бд с таким айди
        Course existEntity = courseRepository.getById(id);

        //Если в бд нет такого айди то вернет null. Значит мы должны выбросить исключение о том что не нашли такого course
        if (existEntity == null) {
            throw new CourseNotFoundException("course not found by id: " + id);
        }

        //Маппинг
        CourseModel existModel = new CourseModel();
        existModel.setId(existEntity.getId());
        existModel.setName(existEntity.getName());

        return existModel;
    }

    @Override
    public List<CourseModel> getAllByCourseName(String courseName) {
        //Валидация
        if (Strings.isBlank(courseName)) {
            throw new InvalidParameterException("course name is blank");
        }

        //Достаем все course по имени
        List<Course> courseEntityList = courseRepository.findAllByCourseName(courseName);

        //Создаем пустой массив моделек
        List<CourseModel> courseModelList = new ArrayList<>();

        //Проходимся по каждому чтобы смаппить все элементы в модельки
        for (Course element : courseEntityList) {
            //Маппим по одному каждый энтити в модель
            CourseModel courseModel = new CourseModel();
            courseModel.setId(element.getId());
            courseModel.setName(element.getName());

            //добавляем в массив моделек
            courseModelList.add(courseModel);
        }

        //Возвращаем модельки
        return courseModelList;
    }
}
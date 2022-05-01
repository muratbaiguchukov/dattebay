package kg.itacademy.testproject.service.impl;

import kg.itacademy.testproject.entity.File;
import kg.itacademy.testproject.exceptions.FileModelNullException;
import kg.itacademy.testproject.model.FileModel;
import kg.itacademy.testproject.repository.FileRepository;
import kg.itacademy.testproject.service.FileService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public FileModel create(FileModel fileModel) {

        //Валидация
        if (fileModel == null) {
            throw new FileModelNullException("Create file model is null");
        } else if (Strings.isBlank(fileModel. getName())) {
            throw new InvalidParameterException("file name can't be blank");
        }

        //Маппинг
        File file = new File();
        file.setName(fileModel.getName());
        file.setUrl(fileModel.getUrl());
        file = fileRepository.save(file);

        // Обратный маппинг
        fileModel.setId(file.getId());

        //Вернуть результат
        return fileModel;
    }

    @Override
    public boolean update(FileModel fileModel) throws FileNotFoundException {
        //Валидация
        if (fileModel == null) {
            throw new FileModelNullException("Create file model is null");
        } else if (fileModel.getName() == null || fileModel.getName().equals("")) {
            throw new InvalidParameterException("file name can't be empty");
        } else if (fileModel.getId() == null) {
            throw new InvalidParameterException("file id can't be null");
        }
        //Проверка на то что есть такой file с таким id
        File existFile = fileRepository.getById(fileModel.getId());
        if (existFile == null) {
            throw new FileNotFoundException("file not found by id " + fileModel.getId());
        }

        //маппим
        existFile.setName(fileModel.getName());

        //обновляем
        existFile = fileRepository.save(existFile);

        //Если id не нулл после обновляения то можем считать что update прошел успешно в бд.
        return existFile.getId() != null;
    }

    @Override
    public boolean deleteById(Long id) throws FileNotFoundException {
        //Валидация
        if (!fileRepository.existsById(id)) {
            throw new FileNotFoundException("File not found by id: " + id);
        }

        //Удаление
        fileRepository.deleteById(id);

        //Вернеться тру если никаких исключений не произошло.
        return true;
    }

    @Override
    public FileModel getById(Long id) throws FileNotFoundException {
        //Валидация
        if (id == null) {
            throw new InvalidParameterException("Id is null");
        }

        //Ищем в бд с таким айди
        File existEntity = fileRepository.getById(id);

        //Если в бд нет такого айди то вернет null. Значит мы должны выбросить исключение о том что не нашли такого file
        if (existEntity == null) {
            throw new FileNotFoundException("file not found by id: " + id);
        }

        //Маппинг
        FileModel existModel = new FileModel();
        existModel.setId(existEntity.getId());
        existModel.setName(existEntity.getName());
        existModel.setUrl(existEntity.getUrl());

        return existModel;
    }

    @Override
    public List<FileModel> getAllByFileName(String fileName) {
        //Валидация
        if (Strings.isBlank(fileName)) {
            throw new InvalidParameterException("file name is blank");
        }

        //Достаем все file по имени
        List<File> fileEntityList = fileRepository.findAllByName(fileName);

        //Создаем пустой массив моделек
        List<FileModel> fileModelList = new ArrayList<>();

        //Проходимся по каждому чтобы смаппить все элементы в модельки
        for (File element : fileEntityList) {
            //Маппим по одному каждый энтити в модель
            FileModel fileModel = new FileModel();
            fileModel.setId(element.getId());
            fileModel.setName(element.getName());
            fileModel.setUrl(element.getUrl());

            //добавляем в массив моделек
            fileModelList.add(fileModel);
        }

        //Возвращаем модельки
        return fileModelList;
    }
}
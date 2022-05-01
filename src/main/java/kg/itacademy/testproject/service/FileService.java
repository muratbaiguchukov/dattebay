package kg.itacademy.testproject.service;

import kg.itacademy.testproject.model.CourseModel;
import kg.itacademy.testproject.model.FileModel;

import java.util.List;

public interface FileService {
    FileModel create(FileModel fileModel);

    boolean update(FileModel fileModel);

    boolean deleteById(Long id);

    FileModel getById(Long id);

    List<FileModel> getAllByFileName(String fileName);
}

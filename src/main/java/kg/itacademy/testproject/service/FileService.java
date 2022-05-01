package kg.itacademy.testproject.service;

import kg.itacademy.testproject.model.CourseModel;
import kg.itacademy.testproject.model.FileModel;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileService {
    FileModel create(FileModel fileModel);

    boolean update(FileModel fileModel) throws FileNotFoundException;

    boolean deleteById(Long id) throws FileNotFoundException;

    FileModel getById(Long id) throws FileNotFoundException;

    List<FileModel> getAllByFileName(String fileName);
}

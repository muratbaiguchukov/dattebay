package kg.itacademy.testproject.service;

import kg.itacademy.testproject.model.CourseModel;
import kg.itacademy.testproject.model.UserAuthModel;
import kg.itacademy.testproject.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel createNewUser(UserModel userModel);

    boolean update(UserModel userModel);

    UserModel userLogin(UserModel userModel);

    boolean deleteById(Long id);

    UserModel getById(Long id);

    List<UserModel> getAllByUsers(String userName);

    String getToken(UserAuthModel userAuthDto);
    String createUser(UserModel userAuthDto);
}
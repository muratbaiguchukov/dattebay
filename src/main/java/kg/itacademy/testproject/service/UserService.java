package kg.itacademy.testproject.service;

import kg.itacademy.testproject.model.UserAuthModel;
import kg.itacademy.testproject.model.UserModel;

import java.util.List;

public interface UserService {

    UserModel createNewUser ( UserModel UserModel );

    Boolean deleteUserById ( Long userId );

    UserModel getUserById ( Long userId );

    UserModel getUserByLogin ( String login);

    List<UserModel> getAllUsers ();

    UserModel userLogin ( UserModel UserModel );

    Boolean updateUser ( UserModel userModel );

    String getToken ( UserAuthModel userAuthDto);
}

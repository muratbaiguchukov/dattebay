package kg.itacademy.testproject.repository;

import kg.itacademy.testproject.entity.User;
import kg.itacademy.testproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u.* from users u where u.user_login = :FindUserByLoginAndPassword or u.user_password = :FindUserByLoginAndPassword", nativeQuery = true)
    User FindUserByLoginPassword ( String findUserByLoginAndPassword );

    User findUserByLogin ( String login );;

    List<UserModel> getAllUsers ();

}
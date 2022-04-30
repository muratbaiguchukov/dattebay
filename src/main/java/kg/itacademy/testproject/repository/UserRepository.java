package kg.itacademy.testproject.repository;

import kg.itacademy.testproject.entity.UserEntity;
import kg.itacademy.testproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "select u.* from users u where u.user_login = :FindUserByLoginAndPassword or u.user_password = :FindUserByLoginAndPassword", nativeQuery = true)
    UserEntity FindUserByLoginPassword ( String findUserByLoginAndPassword );

    UserEntity findUserByLogin ( String login );;

    List<UserModel> getAllUsers ();

}

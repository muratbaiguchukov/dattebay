package kg.itacademy.testproject.repository;

import kg.itacademy.testproject.entity.UserEntity;
import kg.itacademy.testproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "select u.* from users u where u.user_login = :loginOrPass or u.user_password = :loginOrPass", nativeQuery = true)
    UserEntity FindUserByLoginAndPassword ( String loginOrPass );

    @Query()
    UserEntity findUserByLogin ( String login );;

    List<UserModel> getAllUsers ();

}

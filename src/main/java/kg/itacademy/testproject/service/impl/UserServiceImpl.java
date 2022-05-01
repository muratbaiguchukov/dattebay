package kg.itacademy.testproject.service.impl;

import kg.itacademy.testproject.entity.UserEntity;
import kg.itacademy.testproject.exceptions.IdIsNullException;
import kg.itacademy.testproject.exceptions.LoginOrPasswordIsWrongException;
import kg.itacademy.testproject.exceptions.UserNotFoundException;
import kg.itacademy.testproject.model.UserAuthModel;
import kg.itacademy.testproject.model.UserModel;
import kg.itacademy.testproject.repository.RoleRepository;
import kg.itacademy.testproject.repository.UserRepository;
import kg.itacademy.testproject.repository.UserRoleRepository;
import kg.itacademy.testproject.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String getToken ( UserAuthModel userAuthDto )
    {
        UserEntity user = userRepository
                .findUserByLogin ( userAuthDto.getLogin () );
        if ( user == null )
        {
            throw new UsernameNotFoundException ( "Username not found" );
        }
        boolean isMatches = passwordEncoder.matches ( userAuthDto.getPassword (), user.getPassword () );
        if ( isMatches )
        {
            return "Basic " + new String ( Base64.getEncoder ()
                    .encode ( ( user.getLogin () + ":" + userAuthDto.getPassword () ).getBytes () ) );
        } else
        {
            throw new LoginOrPasswordIsWrongException ( "Неправильный логин или пароль!", HttpStatus.UNAUTHORIZED );
        }
    }

    @Override
    public UserModel createNewUser ( UserModel userModel )
    {
        if ( userModel == null )
        {
            throw new RuntimeException ( "userModel is null" );
        } else if ( userModel.getEmail () == null || userModel.getLogin () == null || userModel.getPassword () == null )
        {
            throw new RuntimeException ( "Field can't be null" );
        }

        UserEntity user = new UserEntity ();
        user.setLogin ( userModel.getLogin () );
        user.setPassword ( userModel.getPassword () );
        user.setEmail ( userModel.getEmail () );
        user = userRepository.save ( user );


        return userModel;
    }

    @Override
    public Boolean updateUser ( UserModel userModel )
    {
        return null;
    }

    @Override
    public UserModel getUserById ( Long userId )
    {
        if ( userId == null )
        {
            throw new IdIsNullException ( "Id is null" );
        }

        UserEntity entity = userRepository.getById ( userId );

        if ( entity == null )
        {
            throw new UserNotFoundException ( "User not found by id: " + userId );
        }

        UserModel userModel = new UserModel ();
        userModel.setLogin ( userModel.getLogin () );
        userModel.setPassword ( userModel.getPassword () );
        userModel.setEmail ( userModel.getEmail () );

        return userModel;
    }

    @Override
    public UserModel getUserByLogin ( String login )
    {
        return null;
    }


    @Override
    public UserModel userLogin ( UserModel userModel )
    {
        return null;
    }

    @Override
    public Boolean deleteUserById ( Long userId )
    {
        return null;
    }

    @Override
    public List<UserModel> getAllUsers ()
    {
        List<UserEntity> userEntityList = userRepository.findAll ();

        List<UserModel> userModelList = new ArrayList<> ();

        for (UserEntity user : userEntityList)
        {
            UserModel userModel = new UserModel ();
            userModel.setLogin ( user.getLogin () );
            userModel.setPassword ( user.getPassword () );
            userModel.setEmail ( user.getEmail () );

            userModelList.add ( userModel );
        }

        return userModelList;
    }

}

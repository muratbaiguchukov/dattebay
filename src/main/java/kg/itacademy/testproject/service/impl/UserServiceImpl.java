package kg.itacademy.testproject.service.impl;

import kg.itacademy.testproject.entity.User;
import kg.itacademy.testproject.entity.UserRole;
import kg.itacademy.testproject.exceptions.UserNotCreatedException;
import kg.itacademy.testproject.model.UserAuthModel;
import kg.itacademy.testproject.model.UserModel;
import kg.itacademy.testproject.repository.RoleRepository;
import kg.itacademy.testproject.repository.UserRepository;
import kg.itacademy.testproject.repository.UserRoleRepository;
import kg.itacademy.testproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel createNewUser(UserModel userModel) {
        if(userModel == null)
            throw new UserNotCreatedException("userModel can't be blank ");
        User user = new User();
        user.setLogin(userModel.getLogin());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setIsActive(true);

        user = userRepository.save(user);
        userModel.setId(user.getId());
        return userModel;
    }

    @Override
    public boolean update(UserModel userModel) {
        return false;
    }

    @Override
    public UserModel userLogin(UserModel userModel) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public UserModel getById(Long id) {
        return null;
    }

    @Override
    public List<UserModel> getAllByUsers(String userName) {
        return null;
    }

    @Override
    public String getToken(UserAuthModel userAuthDto) {
        User user = userRepository
                .findByLogin(userAuthDto.getLogin());
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        boolean isMatches = passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword());
        if (isMatches) {
            return "Basic " + new String(Base64.getEncoder()
                    .encode((user.getLogin() + ":" + userAuthDto.getPassword()).getBytes()));
        } else {
            throw new UserSignInException("Неправильный логин или пароль!", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public String createUser(UserModel userModel) {
        User user = new User();
        //Маппинг user
        user.setLogin(userModel.getLogin());
        user.setEmail(userModel.getEmail());
        //
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setIsActive(true);

        UserRole userRole = new UserRole();
        if (userModel.getLogin().contains("admin")) {
            userRole.setRole(roleRepository.findFirstByNameRole("Admin"));
        } else {
            userRole.setRole(roleRepository.findFirstByNameRole("User"));
        }
        userRole.setUser(userRepository.save(user));
        userRoleRepository.save(userRole);
        return "created";
    }
}

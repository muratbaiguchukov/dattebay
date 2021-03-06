package kg.itacademy.testproject.controller;

import kg.itacademy.testproject.model.RoleModel;
import kg.itacademy.testproject.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleController {
    final RoleRepository roleRepository;

    @PostMapping("/create")
    public String createRole(@RequestBody RoleModel roleModel) {
        Role role = new Role();
        role.setNameRole(roleModel.getName());
        return roleRepository.save(role).getNameRole();
    }

}

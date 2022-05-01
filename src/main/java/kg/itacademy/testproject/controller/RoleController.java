package kg.itacademy.testproject.controller;

import kg.itacademy.testproject.entity.RoleEntity;
import kg.itacademy.testproject.model.RoleModel;
import kg.itacademy.testproject.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
    

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleController {
    final RoleRepository roleRepository;

    @PostMapping("/create")
    public String createRole ( @RequestBody RoleModel roleModel )
    {
        RoleEntity role = new RoleEntity ();
        role.setNameRole ( roleModel.getName () );
        return roleRepository.save ( role ).getNameRole ();
    }

}

package kg.itacademy.testproject.repository;


import kg.itacademy.testproject.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findFirstByNameRole ( String roleName );
}

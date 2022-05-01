package kg.itacademy.testproject.repository;


import kg.itacademy.testproject.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query(value = "select r.* from roles r where r.name_role = :roleName", nativeQuery = true)
    RoleEntity findFirstByNameRole ( String roleName );
}

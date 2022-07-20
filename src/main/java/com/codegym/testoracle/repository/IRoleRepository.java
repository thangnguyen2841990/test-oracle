package com.codegym.testoracle.repository;

import com.codegym.testoracle.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select * from roles where name = ?1", nativeQuery = true)
    Role findByName(String name);
}

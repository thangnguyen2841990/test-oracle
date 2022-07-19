package com.codegym.testoracle.repository;

import com.codegym.testoracle.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);
}

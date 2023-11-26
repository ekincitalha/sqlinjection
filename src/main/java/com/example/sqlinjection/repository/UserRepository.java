package com.example.sqlinjection.repository;

import com.example.sqlinjection.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query(value = "SELECT * FROM User  WHERE user.username = :username AND user.password = :password",nativeQuery=true)
    User findByUsernameAndPassword(String username,String password);
}
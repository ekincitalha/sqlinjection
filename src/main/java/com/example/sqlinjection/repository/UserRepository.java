package com.example.sqlinjection.repository;

import com.example.sqlinjection.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    //@Query(value = "SELECT * FROM User  WHERE user.username = :username AND user.password = :password",nativeQuery=true)
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    List<User> findByUsernameAndPassword(String username,String password);

    @Procedure(procedureName = "VerifyUser")
    List<User> unsafeProcedurFindAccountByUsernameAndPassword(String username, String password);
    @Procedure(procedureName = "AuthenticateUser")
    List<User> safeProcedurFindAccountByUsernameAndPassword(String username, String password);
}
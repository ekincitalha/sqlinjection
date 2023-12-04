package com.example.sqlinjection.service;

import com.example.sqlinjection.entity.User;
import com.example.sqlinjection.entity.dto.UserRequestDTO;
import com.example.sqlinjection.repository.UserRepository;
import lombok.RequiredArgsConstructor;


public interface UserService {
    User unsafeJpaFindAccountByUsernameAndPassword(UserRequestDTO userRequestDTO);

    boolean safeProcedurFindAccountByUsernameAndPassword(UserRequestDTO userRequestDTO);

    boolean unsafeProcedurFindAccountByUsernameAndPassword(UserRequestDTO userRequestDTO);

    boolean findByUsernameAndPassword(String username, String password);


}

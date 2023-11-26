package com.example.sqlinjection.service;

import com.example.sqlinjection.entity.dto.UserRequestDTO;

public interface UserService {
   boolean unsafeJpaFindAccountByUsernameAndPassword(UserRequestDTO userRequestDTO);

}

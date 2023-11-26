package com.example.sqlinjection.service;

import com.example.sqlinjection.entity.User;
import com.example.sqlinjection.entity.dto.UserRequestDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    @PersistenceContext
    EntityManager em;

    @Transactional(readOnly = true)
    public boolean unsafeJpaFindAccountByUsernameAndPassword(UserRequestDTO userRequestDTO) {
        String jql = "from User where username = '" + userRequestDTO.getUsername()+"'"+"and password= '"+userRequestDTO.getPassword()+"'";
        TypedQuery<User> q = em.createQuery(jql, User.class);
        return q.getResultList().size()>0;
    }
}

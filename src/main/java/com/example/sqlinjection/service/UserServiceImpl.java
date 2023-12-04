package com.example.sqlinjection.service;

import com.example.sqlinjection.entity.User;
import com.example.sqlinjection.entity.dto.UserRequestDTO;
import com.example.sqlinjection.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @PersistenceContext
    EntityManager em;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User unsafeJpaFindAccountByUsernameAndPassword(UserRequestDTO userRequestDTO) {
        String jql = "from User where username = '" + userRequestDTO.getUsername() + "'" + "and password= '" + userRequestDTO.getPassword() + "'";
        TypedQuery<User> q = em.createQuery(jql, User.class);
        return q.getResultList().size()>0 ?q.getResultList().get(0):null;
    }

    @Override
    @Transactional
    public boolean safeProcedurFindAccountByUsernameAndPassword(UserRequestDTO userRequestDTO) {
        return userRepository.safeProcedurFindAccountByUsernameAndPassword(userRequestDTO.getUsername(), userRequestDTO.getPassword()).size() > 0;
    }

    @Override
    @Transactional
    public boolean unsafeProcedurFindAccountByUsernameAndPassword(UserRequestDTO userRequestDTO) {
        return userRepository.unsafeProcedurFindAccountByUsernameAndPassword(userRequestDTO.getUsername(), userRequestDTO.getPassword()).size() > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).size()>0;
    }
}

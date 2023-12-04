package com.example.sqlinjection.controller;

import com.example.sqlinjection.entity.User;
import com.example.sqlinjection.entity.dto.UserRequestDTO;
import com.example.sqlinjection.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor

public class LoginController {
    private final UserService userService;

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseBody
    public ResponseEntity<Map<String, User>> login(@RequestBody UserRequestDTO userRequestDTO) {
        User user = userService.unsafeJpaFindAccountByUsernameAndPassword(userRequestDTO);
        //boolean isOk = userService.unsafeProcedurFindAccountByUsernameAndPassword(userRequestDTO);
        Map<String, User> response = new HashMap<>();

        if (user!=null) {
            response.put("user", user);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            response.put("message", user);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:4200")
    public String login() {
        return "index.html";
    }


}

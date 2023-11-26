package com.example.sqlinjection.controller;

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
    public ResponseEntity<Map<String, String>> login(@RequestBody UserRequestDTO userRequestDTO) {
        boolean isOk = userService.unsafeJpaFindAccountByUsernameAndPassword(userRequestDTO);
        Map<String, String> response = new HashMap<>();

        if (isOk) {
            response.put("message", "success");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            response.put("message", "user not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:4200")
    public String login() {
        return "index.html";
    }


}

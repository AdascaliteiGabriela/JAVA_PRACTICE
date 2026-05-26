package com.example.task3.controller;

import com.example.task3.dto.RegisterRequest;
import com.example.task3.model.Users;
import com.example.task3.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request){

        try {
            Users user = userService.register(
                    request.getUsername(),
                    request.getPassword()
            );

            return ResponseEntity.ok(user);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

    }
}

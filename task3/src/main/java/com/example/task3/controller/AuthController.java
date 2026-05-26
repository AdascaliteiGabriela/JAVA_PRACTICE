package com.example.task3.controller;

import com.example.task3.dto.UserRequestDTO;
import com.example.task3.dto.UserResponseDTO;
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

    //register user
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO request) {

        Users user = userService.register(
                request.username(),
                request.password()
        );

        return ResponseEntity.ok(
                new UserResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getRole()
                )
        );
    }
}


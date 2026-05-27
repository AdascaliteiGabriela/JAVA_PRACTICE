
package com.example.task3.controller;

import com.example.task3.dto.UserRequestDTO;
import com.example.task3.dto.UserResponseDTO;
import com.example.task3.model.Users;
import com.example.task3.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // admin creates users with role
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO request) {

        Users user = userService.createUserWithRole(
                request.username(),
                request.password(),
                request.role()
        );

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }
}
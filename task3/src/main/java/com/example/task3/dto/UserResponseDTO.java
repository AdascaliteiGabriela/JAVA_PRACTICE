package com.example.task3.dto;
import com.example.task3.model.Role;
public record UserResponseDTO(
        Long id,
        String username,
        Role role
){}


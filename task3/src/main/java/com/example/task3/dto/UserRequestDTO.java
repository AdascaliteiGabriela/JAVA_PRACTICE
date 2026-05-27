package com.example.task3.dto;


import com.example.task3.model.Role;

public record UserRequestDTO(
        String username,
        String password,
        Role role
) {}

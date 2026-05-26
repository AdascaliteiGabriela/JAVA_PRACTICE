package com.example.task3.dto;

import java.time.LocalDateTime;


public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        LocalDateTime createdAt
) {
}

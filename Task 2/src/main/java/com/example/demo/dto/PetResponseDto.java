package com.example.demo.dto;

public record PetResponseDto(Long id,
                             String name, String owner,
                             String type,
                             String race,
                             Integer realAge) {


}

package com.example.demo.dto;

public record PetRequestDto(String name,
                            String owner,
                            String type,
                            String race,
                            Integer realAge) {


}
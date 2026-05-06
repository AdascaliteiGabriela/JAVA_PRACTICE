package com.example.demo.exceptions;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(Long id) {
        super("We could not find the pet with id "+id);
    }
}

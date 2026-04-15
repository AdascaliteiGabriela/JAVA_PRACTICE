package com.example.demo.pet;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetService {

    private final List<Pet> pets = new ArrayList<>();

    public PetService() {
        pets.add(new Pet(1L, "Patrocle", "Gigel", "Dog", "Pug", 12));
        pets.add(new Pet(2L, "Puffi", "Mariana", "Dog", "Bichon", 2));
        pets.add(new Pet(3L, "Carlos", "Karla", "Turtle", "Common", 1));
    }

    public List<Pet> getPets() {
        return List.copyOf(pets);
    }
}

package com.example.demo.service;

import com.example.demo.model.Pet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class PetService {

    private final List<Pet> pets = new ArrayList<>();

    public PetService() {
        pets.add(new Pet(1L, "Patrocle", "Gigel", "Dog", "Pug", 12));
        pets.add(new Pet(2L, "Puffi", "Mariana", "Dog", "Bichon", 2));
        pets.add(new Pet(3L, "Carlos", "Karla", "Turtle", "Common", 1));
    }

    public List<Pet> getPets(String type, Integer realAge, String sort) {
        List<Pet> result = List.copyOf(pets);

        if (type != null) {
            result = result.stream()
                    .filter(pet -> type.equalsIgnoreCase(pet.getType()))
                    .toList();
        }

        if (realAge != null) {
            result = result.stream()
                    .filter(pet -> realAge.equals(pet.getRealAge()))
                    .toList();
        }

        if (sort != null) {
            result = sortPets(result, sort);
        }

        return result;
    }

    private List<Pet> sortPets(List<Pet> pets, String sort) {
        String[] parts = sort.split(",");
        String field = parts[0];
        boolean desc = parts.length > 1 && parts[1].equalsIgnoreCase("desc");

        Comparator<Pet> comparator = switch (field) {
            case "name" -> Comparator.comparing(Pet::getName);
            case "owner" -> Comparator.comparing(Pet::getOwner);
            case "type" -> Comparator.comparing(Pet::getType);
            case "race" -> Comparator.comparing(Pet::getRace);
            case "realAge" -> Comparator.comparing(Pet::getRealAge);
            default -> null;
        };

        if (comparator == null) {
            return pets;
        }

        if (desc) {
            comparator = comparator.reversed();
        }

        return pets.stream()
                .sorted(comparator)
                .toList();
    }


    public List<Pet> getAllPets() {
        return List.copyOf(pets);
    }
}
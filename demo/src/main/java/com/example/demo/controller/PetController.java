package com.example.demo.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="api/pets")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<Pet> getPets(
            @RequestParam(required = false) String type,
            @RequestParam(required = false)String sort,
            @RequestParam(required = false)Integer realAge
    ) {
        List<Pet> pets = petService.getPets();
        if(type!=null){
            pets= pets.stream().filter(pet->type.equalsIgnoreCase(pet.getType())).toList();
        }
        if(realAge!=null){
            pets=pets.stream().filter(pet->realAge==pet.getRealAge()).toList();
        }
        if (sort != null) {
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

            if (comparator != null) {
                if (desc) {
                    comparator = comparator.reversed();
                }
                pets = pets.stream().sorted(comparator).toList();
            }
        }

        return pets;
    }
}

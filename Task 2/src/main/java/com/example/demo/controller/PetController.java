package com.example.demo.controller;

import com.example.demo.dto.PetRequestDto;
import com.example.demo.dto.PetResponseDto;
import com.example.demo.model.Pet;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(path="api/pets")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<PetResponseDto> getAllPets(){
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public PetResponseDto getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }


    @PostMapping
    public PetResponseDto createPet(@RequestBody PetRequestDto dto) {
        return petService.createPet(dto);
    }


    @PutMapping("/{id}")
    public PetResponseDto updatePet(
            @PathVariable Long id,
            @RequestBody PetRequestDto dto
    ) {
        return petService.updatePet(id, dto);
    }


    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }


}

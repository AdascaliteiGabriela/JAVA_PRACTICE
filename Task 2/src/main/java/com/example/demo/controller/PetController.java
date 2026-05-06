package com.example.demo.controller;

import com.example.demo.dto.PetRequestDto;
import com.example.demo.dto.PetResponseDto;
import com.example.demo.model.Pet;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PetResponseDto>> getAllPets(){

        return ResponseEntity.ok( petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity <PetResponseDto>getPetById(@PathVariable Long id) {

        return ResponseEntity.ok(petService.getPetById(id));
    }


    @PostMapping
    public ResponseEntity<PetResponseDto> createPet(@RequestBody PetRequestDto dto) {
        PetResponseDto created = petService.createPet(dto);
        return ResponseEntity.status(201).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PetResponseDto>  updatePet(
            @PathVariable Long id,
            @RequestBody PetRequestDto dto
    ) {
        return ResponseEntity.ok( petService.updatePet(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }


}

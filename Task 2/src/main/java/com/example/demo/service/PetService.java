package com.example.demo.service;

import com.example.demo.dto.PetRequestDto;
import com.example.demo.dto.PetResponseDto;
import com.example.demo.model.Pet;
import com.example.demo.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /* ---------- READ ALL ---------- */
    public List<PetResponseDto> getAllPets() {
        return petRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    /* ---------- READ BY ID ---------- */
    public PetResponseDto getPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        return toResponseDto(pet);
    }

    /* ---------- CREATE ---------- */
    public PetResponseDto createPet(PetRequestDto dto) {
        Pet pet = new Pet(
                dto.getName(),
                dto.getOwner(),
                dto.getType(),
                dto.getRace(),
                dto.getRealAge()
        );

        return toResponseDto(petRepository.save(pet));
    }

    /* ---------- UPDATE ---------- */
    public PetResponseDto updatePet(Long id, PetRequestDto dto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        pet.setName(dto.getName());
        pet.setOwner(dto.getOwner());
        pet.setType(dto.getType());
        pet.setRace(dto.getRace());
        pet.setRealAge(dto.getRealAge());

        return toResponseDto(petRepository.save(pet));
    }

    /* ---------- DELETE ---------- */
    public void deletePet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet not found");
        }
        petRepository.deleteById(id);
    }

    /* ---------- MAPPER ---------- */
    private PetResponseDto toResponseDto(Pet pet) {
        PetResponseDto dto = new PetResponseDto();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setOwner(pet.getOwner());
        dto.setType(pet.getType());
        dto.setRace(pet.getRace());
        dto.setRealAge(pet.getRealAge());
        return dto;
    }
}
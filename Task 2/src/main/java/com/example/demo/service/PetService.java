package com.example.demo.service;

import com.example.demo.dto.PetRequestDto;
import com.example.demo.dto.PetResponseDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.PetNotFoundException;
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

    public List<PetResponseDto> getAllPets() {
        return petRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }


    public PetResponseDto getPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(id));
        return toResponseDto(pet);
    }


    public PetResponseDto createPet(PetRequestDto dto) {
        if (dto.getName() == null || dto.getName().isBlank())
            throw new BadRequestException("You did not complete the pet's name");
        else if (dto.getOwner() == null || dto.getOwner().isBlank())
            throw new BadRequestException("You did not complete the pet's owner");
        else if (dto.getRace() == null || dto.getRace().isBlank())
            throw new BadRequestException("You did not complete the pet's race");
        else if (dto.getType() == null || dto.getType().isBlank())
            throw new BadRequestException("You did not complete the pet's type");

        Integer age = dto.getRealAge();
        if (age == null) {
            throw new BadRequestException("Pet age is required");
        }
        if (age < 0) {
            throw new BadRequestException("Pet age cannot be negative");
        }

            Pet pet = new Pet(
                    dto.getName(),
                    dto.getOwner(),
                    dto.getType(),
                    dto.getRace(),
                    dto.getRealAge()
            );

            return toResponseDto(petRepository.save(pet));
        }


        public PetResponseDto updatePet (Long id, PetRequestDto dto){
            Pet pet = petRepository.findById(id)
                    .orElseThrow(() -> new PetNotFoundException(id));

            pet.setName(dto.getName());
            pet.setOwner(dto.getOwner());
            pet.setType(dto.getType());
            pet.setRace(dto.getRace());
            pet.setRealAge(dto.getRealAge());

            return toResponseDto(petRepository.save(pet));
        }


        public void deletePet (Long id){
            if (!petRepository.existsById(id)) {
                throw new PetNotFoundException(id);
            }
            petRepository.deleteById(id);
        }


        private PetResponseDto toResponseDto (Pet pet){
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

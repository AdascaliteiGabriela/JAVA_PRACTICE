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
        if (dto.name() == null || dto.name().isBlank())
            throw new BadRequestException("You did not complete the pet's name");
        else if (dto.owner() == null || dto.owner().isBlank())
            throw new BadRequestException("You did not complete the pet's owner");
        else if (dto.race() == null || dto.race().isBlank())
            throw new BadRequestException("You did not complete the pet's race");
        else if (dto.type() == null || dto.type().isBlank())
            throw new BadRequestException("You did not complete the pet's type");

        Integer age = dto.realAge();
        if (age == null) {
            throw new BadRequestException("Pet age is required");
        }
        if (age < 0) {
            throw new BadRequestException("Pet age cannot be negative");
        }

            Pet pet = new Pet(
                    dto.name(),
                    dto.owner(),
                    dto.type(),
                    dto.race(),
                    dto.realAge()
            );

            return toResponseDto(petRepository.save(pet));
        }


        public PetResponseDto updatePet (Long id, PetRequestDto dto){
            Pet pet = petRepository.findById(id)
                    .orElseThrow(() -> new PetNotFoundException(id));

            pet.setName(dto.name());
            pet.setOwner(dto.owner());
            pet.setType(dto.type());
            pet.setRace(dto.race());
            pet.setRealAge(dto.realAge());

            return toResponseDto(petRepository.save(pet));
        }


        public void deletePet (Long id){
            if (!petRepository.existsById(id)) {
                throw new PetNotFoundException(id);
            }
            petRepository.deleteById(id);
        }


        private PetResponseDto toResponseDto (Pet pet){
            return new PetResponseDto(
                    pet.getId(),
                    pet.getName(),
                    pet.getOwner(),
                    pet.getType(),
                    pet.getRace(),
                    pet.getRealAge()
            );
        }
    }

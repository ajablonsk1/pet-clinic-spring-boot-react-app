package com.example.petclinicspringbootapp.pet;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/pets")
    ResponseEntity<List<Pet>> getPets(){
        return ResponseEntity.ok().body(petService.getPets());
    }

    @GetMapping("/pets/user")
    ResponseEntity<List<Pet>> getPetsByUser(@RequestParam String email){
        return ResponseEntity.ok().body(petService.getPetsByUserEmail(email));
    }

    @PostMapping("/pets/save")
    ResponseEntity<Pet> savePet(@RequestBody Pet pet){
        return ResponseEntity.ok().body(petService.savePet(pet));
    }

    @PostMapping("/pets/save/owner")
    ResponseEntity<?> addOwnerToPet(@RequestBody OwnerToPetForm ownerToPetForm){
        return ResponseEntity.ok().body(petService.savePet(ownerToPetForm));
    }
}

@Data
class OwnerToPetForm{
    private String email;
    private Pet pet;
}


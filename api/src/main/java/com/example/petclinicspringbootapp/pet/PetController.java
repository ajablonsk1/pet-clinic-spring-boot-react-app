package com.example.petclinicspringbootapp.pet;

import com.example.petclinicspringbootapp.user.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<Pet> savePet(Pet pet){
        return ResponseEntity.ok().body(petService.savePet(pet));
    }
}

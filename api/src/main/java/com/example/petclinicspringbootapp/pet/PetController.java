package com.example.petclinicspringbootapp.pet;

import com.example.petclinicspringbootapp.user.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<List<Pet>> getPetsByUser(@RequestBody AppUser user){
        return ResponseEntity.ok().body(petService.getPetsByUser(user));
    }

    @PostMapping("/pets/save")
    ResponseEntity<Pet> savePet(Pet pet){
        return ResponseEntity.ok().body(petService.savePet(pet));
    }
}

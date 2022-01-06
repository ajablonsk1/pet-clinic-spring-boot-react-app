package com.example.petclinicspringbootapp.user.registration;

import com.example.petclinicspringbootapp.user.AppUser;
import com.example.petclinicspringbootapp.user.Role;
import com.example.petclinicspringbootapp.user.UserService;
import com.example.petclinicspringbootapp.util.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;

    public MessageResponse register(RegistrationRequest request){
        List<Role> roles = new ArrayList<>();
        Role role = new Role(null, "ROLE_CUSTOMER");
        userService.saveRole(role);
        roles.add(role);
        AppUser registeredAppUser = new AppUser(null,
                request.getEmail(),
                request.getPassword(),
                roles);
        return userService.singUpUser(registeredAppUser).getBody();
    }
}

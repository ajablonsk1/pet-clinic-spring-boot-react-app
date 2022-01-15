package com.example.petclinicspringbootapp.user;

import com.example.petclinicspringbootapp.util.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
    ResponseEntity<MessageResponse> singUpUser(AppUser appUser);
}

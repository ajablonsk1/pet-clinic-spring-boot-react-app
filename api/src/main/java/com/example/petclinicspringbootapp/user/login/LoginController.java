package com.example.petclinicspringbootapp.user.login;

import com.example.petclinicspringbootapp.user.security.jwt.JwtResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="signIn")
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<JwtResponse> logIn(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok().body(loginService.logIn(loginRequest));
    }
}

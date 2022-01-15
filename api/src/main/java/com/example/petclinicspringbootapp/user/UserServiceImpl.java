package com.example.petclinicspringbootapp.user;

import com.example.petclinicspringbootapp.user.security.UserDetailsImpl;
import com.example.petclinicspringbootapp.util.MessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user {} to the database", user.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to user {}", roleName, email);
        Optional<AppUser> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            Role role = roleRepo.findByName(roleName);
            user.get().getRoles().add(role);
        }
    }

    @Override
    public AppUser getUser(String email) {
        log.info("Fetching user {}", email);
        return userRepo.findByEmail(email).isPresent() ?
                userRepo.findByEmail(email).get() : null;
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users");

        return userRepo.findAll();
    }

    @Override
    public ResponseEntity<MessageResponse> singUpUser(AppUser appUser) {
        boolean userExists = userRepo.findByEmail(appUser.getEmail())
                .isPresent();
        if(userExists){
            log.error("User with given email is already existing: {}", appUser.getEmail());
            return ResponseEntity.badRequest().body(new MessageResponse("User with given email is already existing!"));
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        userRepo.save(appUser);

        return ResponseEntity.ok().body(new MessageResponse("User registered successfully!"));
    }
}

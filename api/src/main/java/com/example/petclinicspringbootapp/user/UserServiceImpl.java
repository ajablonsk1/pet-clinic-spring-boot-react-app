package com.example.petclinicspringbootapp.user;

import com.example.petclinicspringbootapp.customer.Customer;
import com.example.petclinicspringbootapp.customer.CustomerRepo;
import com.example.petclinicspringbootapp.employee.Employee;
import com.example.petclinicspringbootapp.employee.EmployeeRepo;
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
import java.util.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final CustomerRepo customerRepo;
    private final EmployeeRepo employeeRepo;
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

    public Map<String, List<Role>> getRoles(){
        log.info("Getting all roles!");
        Map<String, List<Role>> roleMap = new HashMap<>();
        userRepo.findAll().forEach(appUser -> roleMap.put(appUser.getEmail(), appUser.getRoles()));
        return roleMap;
    }

    public Integer deleteUser(String email){
        log.info("Deleting user {}", email);
        Customer customer = customerRepo.findCustomerByEmail(email);
        if(customer != null){
            customer.setAppUser(null);
        }
        Employee employee = employeeRepo.findEmployeeByEmail(email);
        if(employee != null){
            employee.setAppUser(null);
        }
        Optional<AppUser> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            return userRepo.deleteAppUserByEmail(email);
        } else{
            log.error("User with given email does not exist: {}", email);
            throw new UsernameNotFoundException("User with given email does not exist!");
        }
    }
}

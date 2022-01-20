package com.example.petclinicspringbootapp;

import com.example.petclinicspringbootapp.appointment.Appointment;
import com.example.petclinicspringbootapp.appointment.AppointmentService;
import com.example.petclinicspringbootapp.customer.Customer;
import com.example.petclinicspringbootapp.customer.CustomerService;
import com.example.petclinicspringbootapp.employee.Employee;
import com.example.petclinicspringbootapp.employee.EmployeeService;
import com.example.petclinicspringbootapp.pet.Pet;
import com.example.petclinicspringbootapp.pet.PetService;
import com.example.petclinicspringbootapp.user.AppUser;
import com.example.petclinicspringbootapp.user.Role;
import com.example.petclinicspringbootapp.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserService userService, PetService petService, EmployeeService employeeService,
                                               CustomerService customerService, AppointmentService appointmentService){
        return args -> {
            Role customer = new Role(null, "ROLE_CUSTOMER");
            Role employee = new Role(null, "ROLE_EMPLOYEE");
            Role admin = new Role(null, "ROLE_ADMIN");

            AppUser customerUser1 = new AppUser(null, "customer1@gmail.com", "1234", new ArrayList<>());
            AppUser customerUser2 = new AppUser(null, "customer2@gmail.com", "1234", new ArrayList<>());
            AppUser employeeUser1 = new AppUser(null, "employee1@gmail.com", "1234", new ArrayList<>());
            AppUser employeeUser2 = new AppUser(null, "employee2@gmail.com", "1234", new ArrayList<>());
            AppUser admin1 = new AppUser(null, "admin@gmail.com", "1234", new ArrayList<>());

            String pattern1 = "dd/MM/YYYY";
            String date11 = "24/01/2019";
            String date22 = "16/02/2015";
            String date33 = "04/01/2021";

            Pet pet1 = new Pet(null, "Pet1", "Dog", "Male", new SimpleDateFormat(pattern1).parse(date11), null, new ArrayList<>());
            Pet pet2 = new Pet(null, "Pet2", "Cat", "Female", new SimpleDateFormat(pattern1).parse(date22), null, new ArrayList<>());
            Pet pet3 = new Pet(null, "Pet3", "Hamster", "Male",new SimpleDateFormat(pattern1).parse(date33), null, new ArrayList<>());

            Customer customer1 = new Customer(null, "Bob", "Smith", "111222333", "customer1@gmail.com",
                    new ArrayList<>(), new ArrayList<>(), customerUser1);
            Customer customer2 = new Customer(null, "Ronald", "Smith", "111222333", "customer2@gmail.com",
                    new ArrayList<>(), new ArrayList<>(), customerUser2);

            Employee employee1 = new Employee(null, "Carlos", "Smith","Veterinarian", "employee1@gmail.com", new ArrayList<>(),
                    "employees/employee1.png", "Lorem ipsum dolor sit amet, consectetur " +
                    "adipiscing elit. Morbi massa dolor, cursus et lectus hendrerit, finibus tempor purus. Etiam " +
                    "nec nunc sit amet ex bibendum tempus eget sit amet arcu.", employeeUser1);
            Employee employee2 = new Employee(null, "Karen", "Smith", "Veterinary technician",  "employee2@gmail.com", new ArrayList<>(),
                    "employees/employee2.png", "Lorem ipsum dolor sit amet, consectetur " +
                    "adipiscing elit. Morbi massa dolor, cursus et lectus hendrerit, finibus tempor purus. Etiam " +
                    "nec nunc sit amet ex bibendum tempus eget sit amet arcu.", employeeUser2);

            userService.saveRole(customer);
            userService.saveRole(employee);
            userService.saveRole(admin);

            userService.saveUser(customerUser1);
            userService.saveUser(customerUser2);
            userService.saveUser(employeeUser1);
            userService.saveUser(employeeUser2);
            userService.saveUser(admin1);

            userService.addRoleToUser(customerUser1.getEmail(), customer.getName());
            userService.addRoleToUser(customerUser2.getEmail(), customer.getName());
            userService.addRoleToUser(employeeUser1.getEmail(), employee.getName());
            userService.addRoleToUser(employeeUser2.getEmail(), employee.getName());
            userService.addRoleToUser(admin1.getEmail(), admin.getName());
            userService.addRoleToUser(admin1.getEmail(), employee.getName());
            userService.addRoleToUser(admin1.getEmail(), customer.getName());

            petService.savePet(pet1);
            petService.savePet(pet2);
            petService.savePet(pet3);


            customerService.saveCustomer(customer1);
            customerService.saveCustomer(customer2);

            petService.addOwnerToPet(pet1.getId(), customer1.getId());
            petService.addOwnerToPet(pet2.getId(), customer1.getId());
            petService.addOwnerToPet(pet3.getId(), customer2.getId());

            customerService.addPetToCustomer(customer1.getEmail(), pet1);
            customerService.addPetToCustomer(customer1.getEmail(), pet2);
            customerService.addPetToCustomer(customer2.getEmail(), pet3);


            employeeService.saveEmployee(employee1);
            employeeService.saveEmployee(employee2);

            String pattern = "dd/MM/YYYY HH:mm";
            String date1 = "24/01/2022 14:30";
            String date2 = "16/02/2022 12:00";
            String date3 = "04/01/2022 13:00";

            Appointment appointment1 = new Appointment(null, new SimpleDateFormat(pattern).parse(date1),
                    employee1, customer1, pet1);
            Appointment appointment2 = new Appointment(null, new SimpleDateFormat(pattern).parse(date2),
                    employee2, customer1, pet2);
            Appointment appointment3 = new Appointment(null, new SimpleDateFormat(pattern).parse(date3),
                    employee2, customer2, pet3);

            appointmentService.saveAppointment(appointment1);
            appointmentService.saveAppointment(appointment2);
            appointmentService.saveAppointment(appointment3);
        };
    }
}

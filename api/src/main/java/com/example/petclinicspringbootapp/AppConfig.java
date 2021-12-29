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

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner commandLineRunner(UserService userService, PetService petService, EmployeeService employeeService,
                                               CustomerService customerService, AppointmentService appointmentService){
        return args -> {
            Role customer = new Role(null, "ROLE_CUSTOMER");
            Role employee = new Role(null, "ROLE_EMPLOYEE");
            Role admin = new Role(null, "ROLE_ADMIN");

            AppUser customerUser1 = new AppUser(null, "customer1", "1234", new ArrayList<>());
            AppUser customerUser2 = new AppUser(null, "customer2", "1234", new ArrayList<>());
            AppUser employeeUser1 = new AppUser(null, "employee1", "1234", new ArrayList<>());
            AppUser employeeUser2 = new AppUser(null, "employee2", "1234", new ArrayList<>());
            AppUser admin1 = new AppUser(null, "admin", "1234", new ArrayList<>());

            Pet pet1 = new Pet(null, "Pet1", null, new ArrayList<>(), "/pets/pet1.png");
            Pet pet2 = new Pet(null, "Pet2", null, new ArrayList<>(), "/pets/pet2.png");
            Pet pet3 = new Pet(null, "Pet3", null, new ArrayList<>(), "/pets/pet3.png");

            Customer customer1 = new Customer(null, "Bob", "Smith", "bob@email.com",
                    new ArrayList<>(), new ArrayList<>(), customerUser1);
            Customer customer2 = new Customer(null, "Ronald", "Smith", "ronald@email.com",
                    new ArrayList<>(), new ArrayList<>(), customerUser2);

            Employee employee1 = new Employee(null, "Carlos", "Smith", "carlos@email.com", new ArrayList<>(),
                    "employees/employee1.png", "Lorem ipsum dolor sit amet, consectetur " +
                    "adipiscing elit. Morbi massa dolor, cursus et lectus hendrerit, finibus tempor purus. Etiam " +
                    "nec nunc sit amet ex bibendum tempus eget sit amet arcu.", employeeUser1);
            Employee employee2 = new Employee(null, "Karen", "Smith", "karen@email.com", new ArrayList<>(),
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

            userService.addRoleToUser(customerUser1.getUsername(), customer.getName());
            userService.addRoleToUser(customerUser1.getUsername(), customer.getName());
            userService.addRoleToUser(employeeUser1.getUsername(), employee.getName());
            userService.addRoleToUser(employeeUser2.getUsername(), employee.getName());
            userService.addRoleToUser(admin1.getUsername(), admin.getName());

            petService.savePet(pet1);
            petService.savePet(pet2);
            petService.savePet(pet3);


            customerService.saveCustomer(customer1);
            customerService.saveCustomer(customer2);

            petService.addOwnerToPet(pet1.getId(), customer1.getId());
            petService.addOwnerToPet(pet2.getId(), customer1.getId());
            petService.addOwnerToPet(pet3.getId(), customer2.getId());

            customerService.addPetToCustomer(customer1.getId(), pet1.getId());
            customerService.addPetToCustomer(customer1.getId(), pet2.getId());
            customerService.addPetToCustomer(customer2.getId(), pet3.getId());


            employeeService.saveEmployee(employee1);
            employeeService.saveEmployee(employee2);

            String pattern = "EEE, d MMM yyyy HH:mm";
            String date1 = "Mon, 24 Jan 2022 14:00";
            String date2 = "Wed, 16 Feb 2022 12:00";
            String date3 = "Tue, 4 Jan 2022 13:00";

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

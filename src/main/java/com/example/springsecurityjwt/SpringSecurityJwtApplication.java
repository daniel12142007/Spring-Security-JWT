package com.example.springsecurityjwt;

import com.example.springsecurityjwt.model.LaptopIs;
import com.example.springsecurityjwt.model.Model;
import com.example.springsecurityjwt.model.Person;
import com.example.springsecurityjwt.model.enusm.Role;
import com.example.springsecurityjwt.model.enusm.State;
import com.example.springsecurityjwt.repository.LaptopRepo;
import com.example.springsecurityjwt.repository.ModelRepo;
import com.example.springsecurityjwt.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class SpringSecurityJwtApplication {
    private final ModelRepo modelRepo;
    private final PersonRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final LaptopRepo laptopRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @PostConstruct
    public void saves() {
        Person person = new Person("daniel", passwordEncoder.encode("daniel"), Role.PREMIUM);
        Model model = new Model("daniel", "ahatzanov", person.getEmail(), person.getPassword(), person.getRole());
        Person person1 = new Person("daniel1", passwordEncoder.encode("daniel1"), Role.ADMIN);
        Model model1 = new Model("daniel1", "ahatzanov1", person1.getEmail(), person1.getPassword(), person1.getRole());
        LaptopIs laptopIs = new LaptopIs();
        laptopIs.setModel(model);
        laptopIs.setModel_name(model.getName());
        laptopIs.setState(State.NEW);
        model.setLaptopIs(List.of(laptopIs));
        repo.saveAll(List.of(person, person1));
        modelRepo.saveAll(List.of(model, model1));
        modelRepo.save(model1);
    }
}
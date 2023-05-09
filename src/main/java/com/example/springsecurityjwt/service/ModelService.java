package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dto.request.ModelRequest;
import com.example.springsecurityjwt.dto.response.ModelResponse;
import com.example.springsecurityjwt.model.Model;
import com.example.springsecurityjwt.model.Person;
import com.example.springsecurityjwt.model.enusm.Role;
import com.example.springsecurityjwt.repository.ModelRepo;
import com.example.springsecurityjwt.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepo modelRepo;
    private final PersonRepo personRepo;
    private final PasswordEncoder passwordEncoder;

    public ModelResponse save(ModelRequest modelRequest) {
        if (modelRepo.existsByEmail(modelRequest.getEmail())) {
            throw new RuntimeException("this email is already have in!");
        }
        Person person = new Person(modelRequest.getEmail(), passwordEncoder.encode(modelRequest.getPassword()), Role.USER);
        Model model = new Model(modelRequest.getName(), modelRequest.getLastName(), modelRequest.getEmail(), person.getPassword(), Role.USER);
        modelRepo.save(model);
        personRepo.save(person);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(model, ModelResponse.class);
    }

    public ModelResponse raisePremium(Long id) {
        modelRepo.raiseModel(id);
        personRepo.raisePerson(id);
        return new ModelMapper().map(modelRepo.findById(id), ModelResponse.class);
    }

    public ModelResponse demotion(Long id) {
        if (personRepo.findById(id).get().getRole() != Role.ADMIN) {
            modelRepo.demotionModel(id);
            personRepo.demotionPerson(id);
            return new ModelMapper().map(modelRepo.findById(id), ModelResponse.class);
        } else {
            throw new RuntimeException("This is admin model");
        }
    }

    public ModelResponse raiseAdmin(Long id, String key) {
        switch (key) {
            case "java" -> {
                if (personRepo.findById(id).get().getRole() != Role.ADMIN) {
                    try {
                        modelRepo.raiseModelAdmin(id);
                        personRepo.raisePersonAdmin(id);
                        return new ModelMapper().map(modelRepo.findById(id), ModelResponse.class);
                    } catch (RuntimeException e) {
                        throw new RuntimeException("error");
                    }
                } else {
                    throw new RuntimeException("This is admin model");
                }
            }
            default -> throw new RuntimeException("password incorrect");
        }
    }

    public List<ModelResponse> getByName(String name) {
        if (name != null) {
            List<Model> models = modelRepo.getByName(name);
            List<ModelResponse> model = new ArrayList<>();
            for (Model m : models) {
                model.add(new ModelMapper().map(m, ModelResponse.class));
            }
            return model;
        } else {
            throw new RuntimeException("You type null");
        }
    }

    public ModelResponse ban(Long id) {
        try {
            if (modelRepo.getById(id).getRole() != Role.ADMIN) {
                List<ModelResponse> modelResponse = new ArrayList<>();
                ModelMapper modelMapper = new ModelMapper();
                modelResponse.add(modelMapper.map(modelRepo.findById(id), ModelResponse.class));
                modelRepo.deleteById(id);
                personRepo.deleteById(id);
                return modelResponse.get(Math.toIntExact(id - 1));
            } else {
                throw new RuntimeException("This is admin model");
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("No such user");
        }
    }
}
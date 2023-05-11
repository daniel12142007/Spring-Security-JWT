package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dto.request.LaptopRequest;
import com.example.springsecurityjwt.dto.response.LaptopResponse;
import com.example.springsecurityjwt.model.LaptopIs;
import com.example.springsecurityjwt.model.enusm.Role;
import com.example.springsecurityjwt.model.enusm.State;
import com.example.springsecurityjwt.repository.LaptopRepo;
import com.example.springsecurityjwt.repository.ModelRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PremiumService {
    private final ModelRepo modelRepo;
    private final LaptopRepo laptopRepo;

    public LaptopResponse saveLaptop(Long id, LaptopRequest laptopRequest) {
        if (modelRepo.findById(id).get().getRole() == Role.PREMIUM || modelRepo.findById(id).get().getRole() == Role.PREMIUM) {
            LaptopIs l = new LaptopIs();
            l.setModel_name(modelRepo.findById(id).get().getLastName());
            l.setState(laptopRequest.getState());
            l.setModel(modelRepo.findById(id).get());
            laptopRepo.save(l);
            return new ModelMapper().map(l, LaptopResponse.class);
        } else {
            throw new RuntimeException("This model is not premium or admin");
        }
    }

    public Long count(Long id) {
        return laptopRepo.count(id);
    }
}
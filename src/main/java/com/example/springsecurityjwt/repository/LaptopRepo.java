package com.example.springsecurityjwt.repository;


import com.example.springsecurityjwt.model.LaptopIs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LaptopRepo extends JpaRepository<LaptopIs,Long> {
}
package com.example.springsecurityjwt.repository;


import com.example.springsecurityjwt.model.LaptopIs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LaptopRepo extends JpaRepository<LaptopIs,Long> {
    @Query("select count(u)/2 from Model u,LaptopIs s where s.model.id=:id")
    Long count(@Param(value = "id") Long id);
}
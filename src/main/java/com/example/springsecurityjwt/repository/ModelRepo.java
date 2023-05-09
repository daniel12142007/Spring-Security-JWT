package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ModelRepo extends JpaRepository<Model, Long> {
    @Query("select case when count(u)>0 then true else false end from Model u where u.email like :email")
    boolean existsByEmail(@Param(value = "email") String email);

    @Modifying
    @Query("update Model u set u.role='PREMIUM' where u.id =:id")
    void raiseModel(@Param(value = "id") Long id);

    @Modifying
    @Query("update Model u set u.role='USER' where u.id =:id")
    void demotionModel(@Param(value = "id") Long id);

    @Modifying
    @Query("update Model u set u.role='ADMIN' where u.id =:id")
    void raiseModelAdmin(@Param(value = "id") Long id);

    @Query("select u from Model u where u.name like %:name%")
    List<Model> getByName(@Param(value = "name") String name);

}
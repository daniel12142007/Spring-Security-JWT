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

    //  This method is needed to upgrade to premium
    @Modifying
    @Query("update Model u set u.role='PREMIUM' where u.id =:id")
    void raiseModel(@Param(value = "id") Long id);

    //    This method is needed to upgrade to user
    @Modifying
    @Query("update Model u set u.role='USER' where u.id =:id")
    void demotionModel(@Param(value = "id") Long id);

    // This method is needed to upgrade tp admin
    @Modifying
    @Query("update Model u set u.role='ADMIN' where u.id =:id")
    void raiseModelAdmin(@Param(value = "id") Long id);

    // This method is needed to find by name
    @Query("select u from Model u where u.name like %:name%")
    List<Model> getByAllName(@Param(value = "name") String name);

    @Query("select u from Model u where u.name =:name")
    Model getByName(@Param(value = "name") String name);

}
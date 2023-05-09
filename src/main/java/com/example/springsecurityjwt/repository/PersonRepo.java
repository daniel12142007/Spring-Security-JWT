package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface PersonRepo extends JpaRepository<Person, Long> {
    //    This method is needed for checking email
    Optional<Person> findByEmail(String email);

    @Query("select case when count(u)>0 then true else false end from Person u  where u.email like :email")
    boolean existsByEmail(@Param(value = "email") String email);

    // This method is needed to upgrade to premium
    @Modifying
    @Query("update Person u set u.role='PREMIUM' where u.id =:id")
    void raisePerson(@Param(value = "id") Long id);

    //    This method is needed to upgrade to user
    @Modifying
    @Query("update Person u set u.role='USER' where u.id =:id")
    void demotionPerson(@Param(value = "id") Long id);

    //This method is needed to upgrade to premium
    @Modifying
    @Query("update Person u set u.role='ADMIN' where u.id =:id")
    void raisePersonAdmin(@Param(value = "id") Long id);
}
package com.healthpulse.backend.api.persist.repository;

import com.healthpulse.backend.api.persist.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoEntity, Long> {

    UserInfoEntity findByPhoneAndBirthdate(String phone, LocalDate birthdate);

    UserInfoEntity findByNameAndGenderAndPhoneAndBirthdate(String name, String gender,String phone, LocalDate birthdate);

    UserInfoEntity findFirstByPhone(String phone);

}

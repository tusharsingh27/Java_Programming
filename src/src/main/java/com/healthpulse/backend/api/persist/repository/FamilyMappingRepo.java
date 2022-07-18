package com.healthpulse.backend.api.persist.repository;

import com.healthpulse.backend.api.persist.entity.FamilyMappingEntity;
import com.healthpulse.backend.api.persist.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyMappingRepo extends JpaRepository<FamilyMappingEntity, Long> {

    Optional<List<FamilyMappingEntity>> findByUserId(Long userId);
    FamilyMappingEntity findByUserIdAndMemberId(Long userId,Long memberId);

}

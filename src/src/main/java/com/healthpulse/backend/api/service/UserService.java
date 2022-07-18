package com.healthpulse.backend.api.service;

import com.healthpulse.backend.api.enums.Gender;
import com.healthpulse.backend.api.enums.Status;
import com.healthpulse.backend.api.exeption.DataConflictException;
import com.healthpulse.backend.api.exeption.DataNotFoundException;
import com.healthpulse.backend.api.model.authentication.RegisterUserRequest;
import com.healthpulse.backend.api.model.user.FamilyProfile;
import com.healthpulse.backend.api.model.user.UpdateAddressRequest;
import com.healthpulse.backend.api.model.user.UserInfoRequest;
import com.healthpulse.backend.api.model.user.UserInfoResponse;
import com.healthpulse.backend.api.persist.entity.AddressEntity;
import com.healthpulse.backend.api.persist.entity.FamilyMappingEntity;
import com.healthpulse.backend.api.persist.entity.UserInfoEntity;
import com.healthpulse.backend.api.persist.repository.AddressRepo;
import com.healthpulse.backend.api.persist.repository.FamilyMappingRepo;
import com.healthpulse.backend.api.persist.repository.UserInfoRepo;
import com.healthpulse.backend.api.utils.ObjectMapper;
import com.healthpulse.backend.api.utils.StringCaseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserInfoRepo userInfoRepo;
    private final AddressRepo addressRepo;
    private final FamilyMappingRepo familyMappingRepo;

    public List<UserInfoResponse> getUserInfo(){
        return ObjectMapper.mapAll(userInfoRepo.findAll(), UserInfoResponse.class);
    }

    public UserInfoResponse getUserInfoById (long id){
        UserInfoEntity entity = userInfoRepo.findById(id)
                 .orElseThrow(()->new DataNotFoundException("User Not Found For ID:"+id));
         UserInfoResponse userInfoResponse = ObjectMapper.map(entity,UserInfoResponse.class);
         userInfoResponse.setName(StringCaseUtil.titleCase(entity.getName()));
         userInfoResponse.setFamilyMembers(getFamilyProfiles(entity.getId()));
         userInfoResponse.setAddress(addressRepo.findAllByUserId(entity.getId()));
         return userInfoResponse;
    }

    public UserInfoResponse getUserInfoByMobile (String mobile){
        UserInfoEntity entity = userInfoRepo.findFirstByPhone(mobile);
        if(entity != null ){
            UserInfoResponse userInfoResponse = ObjectMapper.map(entity,UserInfoResponse.class);
            userInfoResponse.setName(StringCaseUtil.titleCase(entity.getName()));
            userInfoResponse.setFamilyMembers(getFamilyProfiles(entity.getId()));
            userInfoResponse.setAddress(addressRepo.findAllByUserId(entity.getId()));
            return userInfoResponse;
        }
        return new UserInfoResponse();
    }

    public UserInfoResponse addUserInfo (RegisterUserRequest data){
        UserInfoEntity entity;
        data.setName(data.getName().trim().toLowerCase());
        data.setGender(Gender.getGender(data.getGender()).toString());
        entity = userInfoRepo.findByPhoneAndBirthdate(data.getPhone(),data.getBirthdate());
        if(entity != null){
            entity = ObjectMapper.map(data,entity);
            log.info("Requested User already exist in pulse");
        }
        else {
            entity = ObjectMapper.map(data,UserInfoEntity.class);
            entity.setAddress(new ArrayList<>());
            entity.setCreatedDate(LocalDateTime.now());
            entity.setStatus(Status.ACTIVE.toString());
            log.info("Requested User not found, adding in pulse");
        }
        entity.setUpdatedDate(LocalDateTime.now());

        return ObjectMapper.map(userInfoRepo.save(entity),UserInfoResponse.class);
    }

    public UserInfoResponse addFamilyMember(Long userId,UserInfoRequest data){
        UserInfoEntity secondaryUser;
        UserInfoEntity primaryUser = userInfoRepo.getById(userId);
        if(data.getPhone() == null || data.getPhone().length()<10){
            data.setPhone(primaryUser.getPhone());
        }
        data.setName(data.getName().trim().toLowerCase());
        data.setGender(Gender.getGender(data.getGender()).toString());
        secondaryUser = userInfoRepo.findByNameAndGenderAndPhoneAndBirthdate(data.getName(),data.getGender(),data.getPhone(),data.getBirthdate());
        if(secondaryUser != null){
            log.info("Requested User already exist in pulse");
            secondaryUser.setUpdatedDate(LocalDateTime.now());
        }
        else {
            log.info("Requested User not found, adding in pulse");
            secondaryUser = ObjectMapper.map(data,UserInfoEntity.class);
            secondaryUser.setAddress(new ArrayList<>());
            secondaryUser.setCreatedDate(LocalDateTime.now());
            secondaryUser.setUpdatedDate(LocalDateTime.now());
        }
        secondaryUser.setStatus(Status.ACTIVE.toString());
        secondaryUser = userInfoRepo.save(secondaryUser);

        FamilyMappingEntity familyMapping = null;
        familyMapping = familyMappingRepo.findByUserIdAndMemberId(userId, secondaryUser.getId());
        if(familyMapping == null) {
            familyMapping = new FamilyMappingEntity(userId,secondaryUser.getId(),data.getRelation());
            familyMappingRepo.save(familyMapping);
            log.info("Family member added to user:" + primaryUser.getId());
        }
        else
            throw new DataConflictException("Member already linked to current profile");

        UserInfoResponse response = ObjectMapper.map(userInfoRepo.getById(userId),UserInfoResponse.class);
        response.setFamilyMembers(getFamilyProfiles(userId));
        return response;
    }

    public UserInfoResponse removeFamilyMember(Long userId,Long memberId){
        FamilyMappingEntity entity = familyMappingRepo.findByUserIdAndMemberId(userId,memberId);
        if(entity != null){
            familyMappingRepo.deleteById(entity.getId());
        }
        return getUserInfoById(userId);
    }

    private List<FamilyProfile> getFamilyProfiles(long userId){
        List<FamilyProfile> familyProfiles = new ArrayList<>();
        List<FamilyMappingEntity> entities = familyMappingRepo.findByUserId(userId).orElse(new ArrayList<>());
        for(FamilyMappingEntity entity:entities){
            UserInfoEntity userInfoEntity = userInfoRepo.findById(entity.getMemberId())
                    .orElse(null);

            if(userInfoEntity !=null){
                FamilyProfile familyProfile = ObjectMapper.map(userInfoEntity,FamilyProfile.class);
                familyProfile.setRelation(entity.getRelation());
                familyProfile.setName(StringCaseUtil.titleCase(userInfoEntity.getName()));
                familyProfiles.add(familyProfile);
            }
        }
        return familyProfiles;
    }

    public UserInfoResponse updateUserInfo (long id,UserInfoRequest data){
        UserInfoEntity entity = userInfoRepo.findById(id)
                .orElseThrow(()->new DataNotFoundException("User Not Found For ID:"+id));
        if(!data.getName().isEmpty()){
            entity.setName(data.getName().trim().toLowerCase());
        }
        entity.setHealthIdNumber(data.getHealthIdNumber());
        entity.setHealthId(data.getHealthId());
        entity.setAadhaar(data.getAadhaar());
        entity.setPhoto(data.getPhoto());
        entity.setEmail(data.getEmail());
        entity.setCareOf(data.getCareOf());
        entity.setQrCode(data.getQrCode());
        entity.setUpdatedDate(LocalDateTime.now());
        return ObjectMapper.map(userInfoRepo.save(entity),UserInfoResponse.class);
    }

    public UserInfoResponse updateAddress (long id, UpdateAddressRequest data){
        UserInfoEntity entity = userInfoRepo.findById(id)
                .orElseThrow(()->new DataNotFoundException("User Not Found For ID:"+id));
        AddressEntity addressEntity = ObjectMapper.map(data, AddressEntity.class);
        List<AddressEntity> addressEntities = new ArrayList<>();
        if(entity.getAddress() != null){
            addressEntities = entity.getAddress();
        }
        addressEntities.add(addressEntity);
        entity.setAddress(addressEntities);
        userInfoRepo.save(entity);
        return getUserInfoById(data.getUserId());
    }

}

package com.healthpulse.backend.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.healthpulse.backend.api.configuration.AppProperties;
import com.healthpulse.backend.api.enums.Gender;
import com.healthpulse.backend.api.exeption.DataNotFoundException;
import com.healthpulse.backend.api.model.*;
import com.healthpulse.backend.api.model.authentication.*;
import com.healthpulse.backend.api.model.healthSystem.*;
import com.healthpulse.backend.api.persist.entity.UserInfoEntity;
import com.healthpulse.backend.api.persist.repository.UserInfoRepo;
import com.healthpulse.backend.api.utils.RsaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    private final RestTemplate restTemplate;

    private final AppProperties appProperties;
    private final OtpService otpService;
    public static AuthToken authToken;

    private void getToken() throws URISyntaxException {
            if(authToken == null || authToken.isExpired()) {
                log.warn("Token expired!! Creating new");
                URI uri = new URI(appProperties.getSession());

                // create headers
                HttpHeaders headers = new HttpHeaders();

                // request body parameters
                Map<String, Object> map = new HashMap<>();
                map.put("clientId", appProperties.getClientId());
                map.put("clientSecret", appProperties.getClientSecret());

                // build the request
                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
                ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
                if(result.getStatusCode().is2xxSuccessful()){
                    try {
                        JSONObject jsonObj = new JSONObject(result.getBody());
                        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                        authToken =  mapper.readValue(jsonObj.toString(), AuthToken.class);
                    }
                    catch (JsonProcessingException ex){
                        log.error(ex.getMessage());
                    }
                    authToken.setTimestamp(LocalDateTime.now());
                    log.info("Auth Token generated at:"+ authToken.getTimestamp());
                }
                else
                    throw new InternalError("Not getting token from ABDM");
            }

    }

    public GenerateOtpResponse generateOtpForLogin(GenerateOtpRequest request, String type) throws URISyntaxException  {

        if(type.equalsIgnoreCase("pulse")){
            return otpService.generateOTP(request.getValue());
        }

        URI uri = new URI(appProperties.getBaseUrl() + "/registration/");
        if(type.equalsIgnoreCase("mobile"))
            uri = uri.resolve(uri.getPath() + type + "/login/generateOtp");

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken.getAccessToken());

        // request body parameters
        Map<String, Object> body = new HashMap<>();
        if(type.equalsIgnoreCase("aadhaar")){
            body.put(type, RsaUtil.getEncryptedString(request.getValue()));
        }
        else
            body.put(type, request.getValue());

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        log.info(entity.toString());
        ResponseEntity<GenerateOtpResponse> result = restTemplate.postForEntity(uri, entity, GenerateOtpResponse.class);
        return result.getBody();
    }

    public Object verifyOtpForLogin(VerifyOtpRequest request, String type) throws URISyntaxException {

        if(type.equalsIgnoreCase("pulse")){
            return otpService.verifyOTP(request);
        }
        URI uri = new URI(appProperties.getBaseUrl() + "/registration/");

        if(type.equalsIgnoreCase("mobile"))
            uri = uri.resolve(uri.getPath() + type + "/login/verifyOtp");

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken.getAccessToken());
        // request body parameters
        request.setOtp(RsaUtil.getEncryptedString(request.getOtp()));
        // build the request
        HttpEntity<VerifyOtpRequest> entity = new HttpEntity<>(request, headers);
        log.info(entity.toString());
        ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
        return result.getBody();

    }

    public Object generateOtp(GenerateOtpRequest request, String type) throws URISyntaxException {
        log.info("Otp requested for mode:" + type);
        URI uri = new URI(appProperties.getBaseUrl() + "/registration/");

        if (type.equalsIgnoreCase("pulse"))
            return otpService.generateOTP(request.getValue());
        else {
            this.getToken();
            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(authToken.getAccessToken());
            headers.setContentType(MediaType.APPLICATION_JSON);
            //HttpEntity<?> entity = null;
            ResponseEntity<String> result = null;
            if(type.equalsIgnoreCase("aadhaar")){
                uri = uri.resolve(uri.getPath() + type + "/generateOtp");
                GenerateOtpForAadhaarRequest body = new GenerateOtpForAadhaarRequest();
                body.setAadhaar(RsaUtil.getEncryptedString(request.getValue()));
                HttpEntity<GenerateOtpForAadhaarRequest> entity = new HttpEntity<>(body, headers);
                result = restTemplate.postForEntity(uri, entity, String.class);
            }
            else if(type.equalsIgnoreCase("mobile")){
                uri = uri.resolve(uri.getPath() + type + "/generateOtp");
                 GenerateOtpForMobileRequest body = new GenerateOtpForMobileRequest();
                body.setMobile(RsaUtil.getEncryptedString(request.getValue()));
                HttpEntity<GenerateOtpForMobileRequest> entity = new HttpEntity<>(body, headers);
                result = restTemplate.postForEntity(uri, entity, String.class);
            }
            else if (type.equalsIgnoreCase("aadhaar-mobile")) {
                uri = uri.resolve(uri.getPath() + "aadhaar/checkAndGenerateMobileOTP");
                Map<String, String> body= new HashMap<>();
                body.put("mobile", request.getValue());
                body.put("txnId", request.getTxnId());
                HttpEntity<?> entity = new HttpEntity<>(body, headers);
                result = restTemplate.postForEntity(uri, entity, String.class);
            }
            else
                throw new DataNotFoundException("No such method for requested type");

            if(result.getStatusCode().is2xxSuccessful())
                log.info("OTP Generated for mode:" + type + " : "+ request.getValue());
            else
                log.error(result.toString());

            return result.getBody();
        }
    }

    public Object verifyOtp(VerifyOtpRequest request, String type) throws URISyntaxException, JsonProcessingException {
        URI uri = new URI(appProperties.getBaseUrl() + "/registration/");
        if(type.equalsIgnoreCase("aadhaar"))
            uri = uri.resolve(uri.getPath() + type + "/verifyOTP");
        if(type.equalsIgnoreCase("mobile"))
            uri = uri.resolve(uri.getPath() + type + "/verifyOtp");
        if(type.equalsIgnoreCase("aadhaar-mobile"))
            uri = uri.resolve(uri.getPath() + "aadhaar/verifyMobileOTP");

        if (type.equalsIgnoreCase("pulse")) {
            return otpService.verifyOTP(request);
        } else {

            this.getToken();
            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(authToken.getAccessToken());
            // request body parameters
            request.setOtp(RsaUtil.getEncryptedString(request.getOtp()));
            // build the request
            HttpEntity<VerifyOtpRequest> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
            log.info("OTP verified for mode:" + type);
            if(result.getStatusCode().is2xxSuccessful() && type.equalsIgnoreCase("aadhaar")){
                JSONObject jsonObj = new JSONObject(result.getBody());
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                HealthIdResponse data =  mapper.readValue(jsonObj.toString(),HealthIdResponse.class);
                byte[] s = getQRCode(data.getJwt().getToken());
                data.setQrCode(Base64.getEncoder().encodeToString(s));
                return data;
            }
            else
                return result.getBody();
        }
    }

    public byte[] getQRCode(String token) throws URISyntaxException{
        URI uri = new URI(appProperties.getBaseUrl() + "/account/qrCode");

        this.getToken();

        List<HttpMessageConverter<?>> converters = new ArrayList<>(1);
        converters.add(new ByteArrayHttpMessageConverter());
        restTemplate.setMessageConverters(converters);

        MultiValueMap<String,String> headers = new HttpHeaders();
        headers.set("X-Token", "Bearer " + token);
        headers.set("Authorization", "Bearer " + authToken.getAccessToken());
        HttpEntity<byte[]> request = new HttpEntity<>(null, headers);
        //getHeaders() will return HttpHeaders with those parameter

        ResponseEntity<byte[]> response = null;
        try {
            response = this.restTemplate.exchange(uri, HttpMethod.GET, request, byte[].class);
        } catch( HttpServerErrorException hse ){
            throw hse;
        }
        return response.getBody();
    }

    public String resendOtp(String txnId, String type) throws URISyntaxException {
        URI uri = new URI(appProperties.getBaseUrl() + "/registration/" + type);
        if(type.equalsIgnoreCase("aadhaar"))
            uri = uri.resolve(uri.getPath() + "/resendAadhaarOtp");
        if(type.equalsIgnoreCase("mobile"))
            uri = uri.resolve(uri.getPath() + "/resendOtp");

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken.getAccessToken());
        // request body parameters
        Map<String, Object> body = new HashMap<>();
        body.put("txnId", txnId);
        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
        return result.getBody();
    }

    public String createHealthIdViaAadhaar(AadhaarHealthIdRequest request) throws URISyntaxException {
        URI uri = new URI(appProperties.getBaseUrl() + "/registration/aadhaar/createHealthIdByAdhaar");

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken.getAccessToken());

        // build the request
        HttpEntity<AadhaarHealthIdRequest> entity = new HttpEntity<>(request, headers);
        log.info(entity.toString());
        ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
        return result.getBody();
    }

    public String createHealthIdViaMobile(MobileHealthIdRequest request) throws URISyntaxException {
        URI uri = new URI(appProperties.getBaseUrl() + "/registration/mobile/createHidViaMobile");

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken.getAccessToken());

        // build the request
        HttpEntity<MobileHealthIdRequest> entity = new HttpEntity<>(request, headers);
        log.info(entity.toString());
        ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
        return result.getBody();
    }

    public String confirmHealthId(AadhaarHealthIdRequest request, String type) throws URISyntaxException {
        URI uri = new URI(appProperties.getBaseUrl() + "/registration/" + type + "/confirm");

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken.getAccessToken());

        // build the request
        HttpEntity<AadhaarHealthIdRequest> entity = new HttpEntity<>(request, headers);
        log.info(entity.toString());
        ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
        return result.getBody();
    }

    public String linkPHRId(CreatePhrRequest request, String type) throws URISyntaxException {
        URI uri = new URI(appProperties.getBaseUrl() + "/registration/" + type + "/phr-address");

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken.getAccessToken());

        // build the request
        HttpEntity<CreatePhrRequest> entity = new HttpEntity<>(request, headers);
        log.info(entity.toString());
        ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
        return result.getBody();
    }

    public String searchByHid(String id) throws URISyntaxException {
        URI uri = new URI(appProperties.getBaseUrl() + "/search/searchByHealthId");

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken.getAccessToken());

        // build the request
        Map<String, Object> body = new HashMap<>();
        body.put("healthId", id);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        log.info(entity.toString());
        ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
        return result.getBody();
    }

    public String existByHid(String id) throws URISyntaxException {
        URI uri = new URI(appProperties.getBaseUrl() + "/search/searchByHealthId");

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken.getAccessToken());

        // build the request
        Map<String, Object> body = new HashMap<>();
        body.put("healthId", id);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        log.info(entity.toString());
        ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
        return result.getBody();
    }

    public SearchUserResponse searchByMobile(RegisterUserRequest user) throws URISyntaxException {
        URI uri = new URI(appProperties.getBaseUrl() + "/search/searchByMobile");

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        // build the reequest
        JSONObject body = new JSONObject();
        //Map<String, Object> body = new HashMap<>();
        body.put("name", user.getName().toLowerCase());
        body.put("gender", Gender.getGender(user.getGender()));
        body.put("mobile", user.getPhone());
        body.put("yearOfBirth", String.valueOf(user.getBirthdate().getYear()));
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        SearchUserResponse response = new SearchUserResponse();
        ResponseEntity<String> result;
        try{
            result = restTemplate.postForEntity(uri, entity, String.class);
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            result = ResponseEntity.internalServerError().build();
        }

        if(result.getStatusCodeValue() == 200){
            log.info("Health Id found for provided details");
            try {
                JSONObject jsonObj = new JSONObject(result.getBody());
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                response =  mapper.readValue(jsonObj.toString(),SearchUserResponse.class);
            }
            catch (JsonProcessingException ex){
                log.error(ex.getMessage());
            }
            if(response.getName() == null || response.getName() == "") response.setName(user.getName());
            if(response.getGender() == null || response.getGender() == "") response.setGender(Gender.getGender(user.getGender()).toString());
        }
        else{
            log.warn("No Health Id found for provided details");
            response.setName(user.getName());
            response.setGender(Gender.getGender(user.getGender()).toString());
            response.setYearOfBirth(user.getBirthdate().getYear());
        }
        response.setYearOfBirth(user.getBirthdate().getYear());
        return response;
    }

    public String getProvidersList(ProvidersRequest request) throws URISyntaxException {

            URI uri = new URI(appProperties.getConsentManagerUrl() + "/providers");

            this.getToken();
            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(authToken.getAccessToken());
            //TODO to put loginToken

            // request body parameters
            Map<String, Object> body = new HashMap<>();
            body.put("name",request.getName());
            body.put("stateCode",request.getStateCode());
            body.put("districtCode",request.getDistrictCode());

            // build the request
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
            return result.getBody();
    }

    public String getStates() throws URISyntaxException {
        URI uri = new URI(appProperties.getBaseUrl() + "/ha/lgd/states");

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken.getAccessToken());

        // build the request
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        log.info(entity.toString());
        ResponseEntity<String> result = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);

        return result.getBody();
    }

    public String getDistricts(String code) throws URISyntaxException {
        URI uri = new URI(appProperties.getBaseUrl() + "/ha/lgd/districts?stateCode="+ code);

        this.getToken();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken.getAccessToken());

        // build the request
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        log.info(entity.toString());
        ResponseEntity<String> result = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);

        return result.getBody();
    }

    public SearchUserResponse userHidLookup(RegisterUserRequest request,Long userId) throws URISyntaxException {
        SearchUserResponse response = new SearchUserResponse();
        if(request != null){
            response = searchByMobile(request);
        }
        if(response.getHealthIdNumber()==null && userId >0){
            log.info("Searching Health Id in Pulse system");
            if(userInfoRepo.existsById(userId)){
                UserInfoEntity entity = userInfoRepo.findById(userId).get();
                if(entity.getHealthIdNumber() != null && entity.getHealthIdNumber() != "") {
                    response.setHealthIdNumber(entity.getHealthIdNumber());
                }
                if(entity.getHealthId() != null && entity.getHealthId() != "") {
                    response.setHealthId(entity.getHealthId());
                }
            }
        }
        return response;
    }

}


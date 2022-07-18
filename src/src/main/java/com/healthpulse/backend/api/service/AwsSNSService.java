package com.healthpulse.backend.api.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Component
@Slf4j
public class AwsSNSService {


    private static final String AWS_ACCESS_KEY = "aws.accessKeyId";
    private static final String AWS_SECRET_KEY = "aws.secretKey";
    static {
        System.setProperty(AWS_ACCESS_KEY, "AKIA34OEGKHTBTIXR5RJ");
        System.setProperty(AWS_SECRET_KEY, "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");
    }
    public static void pubTextSMS(String message, String phoneNumber) {
        try {


               SnsClient snsClient = SnsClient.builder()
                       .region(Region.AP_SOUTH_1)
                       .build();

               PublishRequest request = PublishRequest.builder()
                        .message(message)
                        .phoneNumber(phoneNumber)
                        .build();

            PublishResponse result =  snsClient.publish(request);
            log.info(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());
            snsClient.close();
        } catch (SnsException e) {
            log.error(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}

package com.healthpulse.backend.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsConfig {

    // Value is populated with the aws access key.
    @Value("${aws.accessKeyId}")
    private String awsAccessKey;

    // Value is populated with the aws secret key
    @Value("${aws.secretKey}")
    private String awsSecretKey;

    // Value is populated with the aws region code
//    @Value("${cloud.aws.region.static}")
//    private String region;

    // @Primary annotation gives a higher preference to a bean (when there are multiple beans of the same type).
//    @Primary
//    // @Bean annotation tells that a method produces a bean that is to be managed by the spring container.
//    @Bean
//    public SnsClient snsClient() {
//        return SnsClient.builder()
//                .region(Region.AP_SOUTH_1)
//                .build();
//    }

//    @Primary
//    @Bean
//    public AmazonSNSClient getSnsClient() {
//        return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
//                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY,
//                        SECRET_KEY)))
//                .build();
//    }

}

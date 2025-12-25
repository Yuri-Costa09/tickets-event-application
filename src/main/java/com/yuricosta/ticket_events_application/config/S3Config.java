package com.yuricosta.ticket_events_application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;

@Configuration
public class S3Config {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    /**
     * Creates and configures an S3AsyncClient bean for interacting with Amazon S3.
     * We are using S3AsyncClient API for enhanced performance, by using non-blocking I/O operations.
     * Since images are probably going to be larger than 8mb, the Oficcial Documentation recommends it.
     * @return S3AsyncClient bean
     */
    @Bean
    public S3AsyncClient s3Client() {
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(
                accessKey,
                secretKey
        );
        return S3AsyncClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .multipartEnabled(true)
                .build();
    }
}

package ru.itpark.documentservice.infrastructure.config.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

// MinIO Configuration
@Configuration
public class MinioConfig {
    
    @Value("${minio.url}")
    private String minioUrl;
    
    @Value("${minio.access-key}")
    private String minioAccessKey;
    
    @Value("${minio.secret-key}")
    private String minioSecretKey;
    
    @Bean
    public MinioClient minioClient() {
        System.out.println("minioUrl: " + minioUrl);
        System.out.println("minioAccessKey: " + minioAccessKey);
        System.out.println("minioSecretKey: " + minioSecretKey);

        return MinioClient.builder()
               .endpoint(minioUrl)
               .credentials(minioAccessKey, minioSecretKey)
               .build();

//        return S3Client.builder()
//                .endpointOverride(URI.create(minioUrl))
//                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(minioAccessKey, minioSecretKey)))
//                .region(Region.AP_SOUTH_1)
//                .serviceConfiguration(S3Configuration.builder()
//                        .pathStyleAccessEnabled(true) // Обязательно для MinIO
//                        .build())
//                .build();

    }

//    @Bean
//    public S3Presigner s3Presigner() {
//        return S3Presigner.builder()
//                .region(Region.AP_SOUTH_1)
//                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(minioAccessKey, minioSecretKey)))
//                .endpointOverride(URI.create(minioUrl)) // Укажите, если используете кастомный S3-совместимый сервис, например MinIO
//                .build();
//    }
}


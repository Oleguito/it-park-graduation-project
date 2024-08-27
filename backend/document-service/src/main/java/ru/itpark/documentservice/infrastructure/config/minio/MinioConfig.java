package ru.itpark.documentservice.infrastructure.config.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    }
}


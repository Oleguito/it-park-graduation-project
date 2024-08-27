package ru.itpark.documentservice.application.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;

@Service
public class MinioService {
    
    @Value("${minio.bucket-name}")
    private String bucketName;
    
    @Autowired
    private MinioClient minioClient;
    
    public void uploadFile(String userId, String projectId, MultipartFile file) throws Exception {
        
        createBucketIfNotExists();
        
        String fileName = file.getOriginalFilename();
        String objectName = "u" + userId + "/" + "p" + projectId + "/" + fileName;
        InputStream fileInputStream = file.getInputStream();
        
        // Upload the file to specified bucket with original name
        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(fileInputStream, fileInputStream.available(), -1)
                .contentType(file.getContentType())
            .build()
        );
    }
    
    private void createBucketIfNotExists() {
        if(!bucketExists()) {
            attemptCreateBucket();
            System.out.println("Bucket was created successfully");
        }
    }
    
    private void attemptCreateBucket() {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                                   .bucket(bucketName)
                                   .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean bucketExists() {
        boolean bucketExists = false;
        try {
            bucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
                                                    .bucket(bucketName)
                                                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bucketExists;
    }
}

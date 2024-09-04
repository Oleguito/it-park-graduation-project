package ru.itpark.documentservice.application.service.minio;

import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import ru.itpark.documentservice.application.service.documents.DocumentsService;
import ru.itpark.documentservice.domain.documentservice.Document;

import java.io.InputStream;
import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.badRequest;

@Service
@RequiredArgsConstructor
public class MinioService {

    @Value("${minio.bucket-name}")
    private String bucketName;

    private final DocumentsService documentsService;

//    private final S3Presigner s3Presigner;
//
//    public MinioService(S3Client s3Client, S3Presigner s3Presigner) {
////        this.s3Client = s3Client;
//        this.s3Presigner = s3Presigner;
//    }

    private final String pattern = "u%s/p%s/%s";

    @Autowired
    private MinioClient minioClient;

    public String downloadFile(String userId, String projectId, String fileName) {

        try {

            String uri = String.format(pattern, userId, projectId, fileName);

            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(uri)
                    .expiry(600)
                    .build();
            return minioClient.getPresignedObjectUrl(args);

//            GetObjectArgs argsFile = GetObjectArgs.builder()
//                    .bucket(bucketName)
//                    .object(uri)
//                    .build();
//
//            GetObjectResponse file = minioClient.getObject(argsFile);
//
//            // Create a ResponseEntity with the download URL
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(file.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public void uploadFile(String userId, String projectId, MultipartFile file) throws Exception {

        createBucketIfNotExists();

        String fileName = file.getOriginalFilename();
        String objectName = String.format(pattern, userId, projectId, fileName);
        InputStream fileInputStream = file.getInputStream();

//         Upload the file to specified bucket with original name
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(fileInputStream, fileInputStream.available(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .build();
        String link = minioClient.getPresignedObjectUrl(args);

        documentsService.saveFile(Document.builder()
                        .createdAt(LocalDateTime.now())
                        .fileName(objectName)
                        .projectId(Long.valueOf(projectId))
                        .userId(Long.valueOf(userId))
                        .link(link)
                .build());

//        byte[] fileBytes = file.getBytes();
//        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("test"))) {
//            objectOutputStream.writeObject(fileBytes);
//        }
//
//        PutObjectResponse putObjectResponse = minioClient.putObject(PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(objectName)
//                .build(), Paths.get("test"));
//
//        Paths.get("test").toFile().delete();
    }

//    public ResponseInputStream<GetObjectResponse> downloadFile(String key) {
//        return minioClient.getObject(GetObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build());
//    }

    private void createBucketIfNotExists() {
        if (!bucketExists()) {
            attemptCreateBucket();
            System.out.println("Bucket was created successfully");
        }
    }

    private void attemptCreateBucket() {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
//            minioClient.createBucket(CreateBucketRequest.builder()
//                    .bucket(bucketName)
//                    .build());
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

//            HeadBucketRequest headBucketRequest = HeadBucketRequest.builder()
//                    .bucket(bucketName)
//                    .build();
//
//            minioClient.headBucket(headBucketRequest);
//
//            bucketExists = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bucketExists;
    }

//    public String generatePresignedUrl(String key) {
//        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();
//
//        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
//                .getObjectRequest(getObjectRequest)
//                .signatureDuration(Duration.ofMinutes(10))
//                .build();
//
//        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(getObjectPresignRequest);
//
//        return presignedRequest.url().toString();
//    }

}

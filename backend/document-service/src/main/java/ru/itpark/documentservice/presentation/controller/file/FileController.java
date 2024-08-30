package ru.itpark.documentservice.presentation.controller.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.documentservice.application.minio.MinioService;

@RestController
@RequestMapping("/api/files")
public class FileController {
    
    @Autowired
    private MinioService minioService;
    
    @CrossOrigin
    @RequestMapping(
        path = "/upload",
        method = RequestMethod.POST
    )
    public ResponseEntity<String> uploadFile(
        @RequestParam("userId") String userId,
        @RequestParam("projectId") String projectId,
        @RequestParam("file") MultipartFile file
    ) {
        System.out.println("we are in controller, uploading files...");
        try {
            minioService.uploadFile(userId, projectId, file);
            return ResponseEntity
                   .status(HttpStatus.OK)
                   .body("File uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("File upload failed.");
        }
    }
}

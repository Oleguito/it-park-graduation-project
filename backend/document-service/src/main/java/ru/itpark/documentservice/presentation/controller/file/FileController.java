package ru.itpark.documentservice.presentation.controller.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.documentservice.application.service.documents.DocumentsService;
import ru.itpark.documentservice.application.service.minio.MinioService;
import ru.itpark.documentservice.presentation.controller.file.dto.queries.DocumentsSearchQuery;
import ru.itpark.documentservice.presentation.controller.file.dto.responses.DocumentResponse;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    
    @Autowired
    private MinioService minioService;
    private final DocumentsService documentsService;
    
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
    
    @CrossOrigin
    @RequestMapping(
    path = "/delete",
    method = RequestMethod.DELETE
    )
    public ResponseEntity<String> deleteFile(
        @RequestParam("userId") String userId,
        @RequestParam("projectId") String projectId,
        @RequestParam("fileName") String fileName
    ) {
        System.out.println("we are in controller, deleting files...");
        try {
            minioService.deleteFile(userId, projectId, fileName);
            return ResponseEntity
                   .status(HttpStatus.OK)
                   .body("File deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("File deletion failed.");
        }
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<String> downloadFile(@RequestParam("userId") String userId,
                               @RequestParam("projectId") String projectId,
                               @RequestParam("fileName") String fileName) {
        return ResponseEntity.ofNullable(minioService.downloadFile(userId, projectId, fileName));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/list")
    @ResponseBody
    @CrossOrigin
    public List<DocumentResponse> getLinks(@RequestBody DocumentsSearchQuery documentsSearchQuery) {
        return documentsService.getLinks(documentsSearchQuery);
    }

}

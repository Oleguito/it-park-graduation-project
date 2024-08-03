package ru.itpark.notificationservice.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itpark.notificationservice.application.service.notification.NotificationService;
import ru.itpark.notificationservice.domain.notification.Notification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = "/api/notifications")
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getUserNotifications(@RequestParam Long userId) {
        List<Notification> notifications = notificationService.getUserNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable Long id, @RequestParam Long userId) {
        Notification notification = notificationService.getNotification(id, userId);
        return ResponseEntity.ok(notification);
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNotification);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        notification.setId(id);
        Notification updatedNotification = notificationService.updateNotification(notification);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id, @RequestParam Long userId) {
        notificationService.deleteNotification(id, userId);
        return ResponseEntity.noContent().build();
    }

}


package ru.itpark.notificationservice.presentation.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itpark.notificationservice.application.service.notification.NotificationService;
import ru.itpark.notificationservice.domain.notification.Notification;
import ru.itpark.notificationservice.infrastructure.config.mapper.mapstruct.NotificationMapper;
import ru.itpark.notificationservice.presentation.notification.dto.command.NotificationCreateCommand;
import ru.itpark.notificationservice.presentation.notification.dto.command.NotificationUpdateCommand;
import ru.itpark.notificationservice.presentation.notification.dto.query.NotificationQuery;

import java.util.List;

@RestController
@RequestMapping(value = "/api/notifications")
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;

    private final NotificationMapper notificationMapper;


    public NotificationController(NotificationService notificationService, NotificationMapper notificationMapper) {
        this.notificationService = notificationService;
        this.notificationMapper = notificationMapper;
    }

    @GetMapping
    public ResponseEntity<List<NotificationQuery>> getUserNotifications(@RequestParam Long userId) {
        List<Notification> notifications = notificationService.getUserNotifications(userId);
        return ResponseEntity.ok(notificationMapper.toQueryList(
                notifications
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationQuery> getNotification(@PathVariable Long id, @RequestParam Long userId) {
        Notification notification = notificationService.getNotification(id, userId);
        return ResponseEntity.ok(
                notificationMapper.toQuery(notification)
        );
    }

    @PostMapping
    public ResponseEntity<NotificationQuery> createNotification(@RequestBody NotificationCreateCommand notificationCreateCommand) {
        Notification createdNotification = notificationService.createNotification(notificationMapper.toEntity(
                notificationCreateCommand
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                notificationMapper.toQuery(
                        createdNotification
                )
        );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NotificationQuery> updateNotification(@PathVariable Long id, @RequestBody NotificationUpdateCommand notificationUpdateCommand) {
        notificationUpdateCommand.setId(id);
        Notification updatedNotification = notificationService.updateNotification(
                notificationMapper.toEntity(notificationUpdateCommand)
        );
        return ResponseEntity.ok(
                notificationMapper.toQuery(
                        updatedNotification
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id, @RequestParam Long userId) {
        notificationService.deleteNotification(id, userId);
        return ResponseEntity.noContent().build();
    }

}


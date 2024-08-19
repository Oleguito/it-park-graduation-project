package ru.itpark.notificationservice.application.service.notification;

import org.springframework.stereotype.Service;
import ru.itpark.notificationservice.domain.notification.Notification;
import ru.itpark.notificationservice.infrastructure.repository.NotificationRepository;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public Notification getNotification(Long id, Long userId) {
        return notificationRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public Notification getByKey(UUID key) {
        return notificationRepository.findByIdempotentKey(key);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id, Long userId) {
        notificationRepository.deleteByIdAndUserId(id, userId);
    }

}


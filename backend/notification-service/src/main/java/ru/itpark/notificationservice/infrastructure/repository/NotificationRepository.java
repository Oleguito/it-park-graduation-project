package ru.itpark.notificationservice.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import ru.itpark.notificationservice.domain.notification.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


    List<Notification> findByUserId(Long userId);

    Optional<Notification> findByIdAndUserId(Long id, Long userId);

    @Transactional
    void deleteByIdAndUserId(Long id, Long userId);

}


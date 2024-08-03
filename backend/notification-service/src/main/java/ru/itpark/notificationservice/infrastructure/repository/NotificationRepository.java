package ru.itpark.notificationservice.infrastructure.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itpark.notificationservice.domain.notification.Notification;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


//    List<Notification> findByReadIsFalseAndUserId(Integer userId);

//    @Query("SELECT n FROM Notification n WHERE n.read = false AND n.userId = :userId")
//    List<Notification> findUnreadNotificationsByUserId(@Param("userId") Integer userId);
//
//    List<Notification> findByReadIsFalseAndUserId(@Param("userId") Integer userId);

    List<Notification> findByUserId(Long userId);

    Optional<Notification> findByIdAndUserId(Long id, Long userId);

//    List<Notification> findByReadIsFalse(Long userId);

    @Transactional
    void deleteByIdAndUserId(Long id, Long userId);

}


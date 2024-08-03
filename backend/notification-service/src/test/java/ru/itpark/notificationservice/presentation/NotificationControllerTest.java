package ru.itpark.notificationservice.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.itpark.notificationservice.application.service.notification.NotificationService;
import ru.itpark.notificationservice.domain.notification.Notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    private Notification testNotification;
    private List<Notification> testNotifications;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testNotification = new Notification();
        testNotification.setId(1L);
        testNotification.setUserId(1L);
        testNotification.setTitle("Test Notification");
        testNotification.setMessage("This is a test notification message.");
        testNotification.setCreatedAt(LocalDateTime.now());

        testNotifications = new ArrayList<>();
        testNotifications.add(testNotification);
    }

    @Test
    void testGetUserNotifications() {
        when(notificationService.getUserNotifications(1L)).thenReturn(testNotifications);

        ResponseEntity<List<Notification>> response = notificationController.getUserNotifications(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testNotifications, response.getBody());
    }

    @Test
    void testGetNotification() {
        when(notificationService.getNotification(1L, 1L)).thenReturn(testNotification);

        ResponseEntity<Notification> response = notificationController.getNotification(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testNotification, response.getBody());
    }

    @Test
    void testCreateNotification() {
        when(notificationService.createNotification(testNotification)).thenReturn(testNotification);

        ResponseEntity<Notification> response = notificationController.createNotification(testNotification);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testNotification, response.getBody());
    }

    @Test
    void testUpdateNotification() {
        when(notificationService.updateNotification(testNotification)).thenReturn(testNotification);

        ResponseEntity<Notification> response = notificationController.updateNotification(1L, testNotification);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testNotification, response.getBody());
    }

    @Test
    void testDeleteNotification() {
        notificationController.deleteNotification(1L, 1L);

        verify(notificationService, times(1)).deleteNotification(1L, 1L);
    }

    @Test
    void testGetNotificationWithInvalidId() {
        when(notificationService.getNotification(2L, 1L)).thenReturn(null);

        ResponseEntity<Notification> response = notificationController.getNotification(2L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreateNotificationWithInvalidData() {
        Notification invalidNotification = new Notification();

        when(notificationService.createNotification(invalidNotification))
                .thenThrow(new IllegalArgumentException("Invalid notification data"));

        try {
            notificationController.createNotification(invalidNotification);
        } catch (Exception e) {
            assertEquals("Invalid notification data", e.getMessage());
            return;
        }

        fail("Expected IllegalArgumentException to be thrown");
    }
}

package com.amensar.notification_microservice.service;

import com.amensar.notification_microservice.model.Notification;
import com.amensar.notification_microservice.repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;


import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepo repo;
    public void sendNotification(Notification notification) {
       repo.save(notification);
    }

    public Notification getNotification(int clientId) {
        return repo.findByClientId(clientId);
    }

    public List<Notification> getAllNotifications() {
        return repo.findAll();
    }

    @KafkaListener(topics = "maintenance-updated", groupId = "service-notification-group")
    public void consume(String maintenanceEvent) {
        System.out.println("Événement reçu : " + maintenanceEvent);

        // Logique pour créer la notification
    }
}

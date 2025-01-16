package com.amensar.notification_microservice.repo;

import com.amensar.notification_microservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Integer> {
    Notification findByClientId(int clientId);
}

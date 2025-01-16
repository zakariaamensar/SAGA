package com.amensar.notification_microservice.controller;

import com.amensar.notification_microservice.model.Notification;
import com.amensar.notification_microservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping("notifications")
    public List<Notification> getNotifications(){
        return service.getAllNotifications();
    }

    @PostMapping("notifications")
    public void sendNotification(@RequestBody Notification notification){
        System.out.println(notification.toString());
        service.sendNotification(notification);
    }

    @GetMapping("notifications/{clientId}")
    public Notification getNotification(@PathVariable int clientId){
        return service.getNotification(clientId);

    }
}

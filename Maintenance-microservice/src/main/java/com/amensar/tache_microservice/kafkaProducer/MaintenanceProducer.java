package com.amensar.tache_microservice.kafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceProducer {

    private static final String TOPIC = "maintenance-updated";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMaintenanceUpdate(String maintenanceEvent) {
        kafkaTemplate.send(TOPIC, maintenanceEvent);
    }
}

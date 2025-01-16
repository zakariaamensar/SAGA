package com.amensar.tache_microservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tache {
    @Id
    private int id;
    private Date debut;
    private Date fin;
    private String description;
    private int vehiculeId;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}

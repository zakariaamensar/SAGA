package com.amensar.vehicule_microservice.model;

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
public class Vehicule {

    @Id
    private int vin;
    private int matricule;
    private String marque;
    private String Modèle;
    private int annèe;
    private String couleur;
    private int kilométrage;
    private String typeCarburant;
    private Date dateAchat;
    private int ownerId;
//    @Enumerated(EnumType.STRING)
    private String status;

}



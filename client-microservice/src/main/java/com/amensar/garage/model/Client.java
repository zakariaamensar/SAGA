package com.amensar.garage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    private int id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
}
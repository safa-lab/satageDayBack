package com.integrationProject.managementTrainer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "entreprises")
// Entrepreneur.java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Entreprise  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private String email;
    private String phoneNumber;



    // Additional entrepreneur-specific fields
}

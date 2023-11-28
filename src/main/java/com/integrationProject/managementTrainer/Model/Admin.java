package com.integrationProject.managementTrainer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "admins")
// Admin.java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Admin  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;



    // Additional admin-specific fields
}

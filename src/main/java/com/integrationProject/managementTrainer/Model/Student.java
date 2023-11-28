package com.integrationProject.managementTrainer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Student.java
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Student  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
private String firstName;
private String lastName;
    private String password;
    private String phoneNumber;



}

package com.integrationProject.managementTrainer.Repo;

import com.integrationProject.managementTrainer.Model.Admin;
import com.integrationProject.managementTrainer.Model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface adminRepo extends JpaRepository<Admin, Long> {


    Optional<Admin> findByEmailAndPassword(String username, String password);
    Optional<Admin> findByEmail(String email);
}


package com.integrationProject.managementTrainer.Repo;

import com.integrationProject.managementTrainer.Model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface entrepriseRepo extends JpaRepository<Entreprise, Long> {


    Optional<Entreprise> findByEmailAndPassword(String username, String password);
Optional<Entreprise> findByEmail(String email);
}


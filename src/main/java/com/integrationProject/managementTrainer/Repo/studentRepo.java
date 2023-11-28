package com.integrationProject.managementTrainer.Repo;

import com.integrationProject.managementTrainer.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface studentRepo extends JpaRepository<Student, Long> {
  //  Optional<Student> findByUsername(String username);
    Optional<Student> findByEmailAndPassword(String email,String password);
  public Optional<Student> findByEmail(String email);

}

package com.integrationProject.managementTrainer.controllers;

import com.integrationProject.managementTrainer.Model.Admin;
import com.integrationProject.managementTrainer.Model.Entreprise;
import com.integrationProject.managementTrainer.Model.Student;
import com.integrationProject.managementTrainer.Service.*;
import com.integrationProject.managementTrainer.dto.AdminDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// AuthController.java
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200") // Replace with your Angular app's URL
public class AuthController {


    private final studentService studentS;

    private final entrepriseService entrepriseS;

    private final adminService adminS;

    @PostMapping("/registerStudent")
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        studentS.registerStudent(student);
        return new ResponseEntity<>("Étudiant enregistré avec succès", HttpStatus.CREATED);
    }

    @PostMapping("/loginStudent")
    public ResponseEntity<String> loginStudent(@RequestBody Student student) {
        if (studentS.validateStudent(student.getEmail(), student.getPassword())) {
            return new ResponseEntity<>("Connexion réussie", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nom d'utilisateur ou mot de passe incorrect", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/registerCompany")
    public ResponseEntity<String> registerEtreprise(@RequestBody Entreprise entreprise) {
        log.info("inside register_entreprise{}",entreprise);
        entrepriseS.registerEntreprise(entreprise);
        return new ResponseEntity<>("Étudiant enregistré avec succès", HttpStatus.OK);
    }
    @PostMapping("/loginEntreprise")
    public ResponseEntity<String> loginEntreprise(@RequestBody Entreprise entreprise) {
        if (entrepriseS.validateEntreprise(entreprise.getEmail(), entreprise.getPassword())) {
            return new ResponseEntity<>("Connexion réussie", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nom d'utilisateur ou mot de passe incorrect", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/loginAdmin")
    public ResponseEntity<AdminDto> loginAdmin(@RequestBody Admin admin) {
     return adminS.loginAdmin(admin);
    }

  @GetMapping("/students/all")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students=studentS.findAllStudent();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @GetMapping("/entreprises/all")
    public ResponseEntity<List<Entreprise>> getAllEntreprises(){
        List<Entreprise> entreprises=entrepriseS.findAllStudent();
        return new ResponseEntity<>(entreprises, HttpStatus.OK);
    }

    @DeleteMapping("/company/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        return entrepriseS.deleteCompany(id);
    }
    @DeleteMapping("/student/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        return studentS.deleteStudent(id);
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(
            @RequestBody Map<String,String> request
    ){

        return studentS.forgetPassword(request);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentByStudentId(@PathVariable Long id){
        return studentS.getStudentByStudentId(id);
    }
    @GetMapping("/company/{id}")
    public ResponseEntity<Entreprise> getCompanyBycompanyId(@PathVariable Long id){
        return entrepriseS.getCompanyBycompanyId(id);
    }
    @PostMapping("/updateStudent/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody Map<String,String> request,@PathVariable Long id){
        return studentS.updateStudent(request,id);
    }
    @PostMapping("/updateCompany/{id}")
    public ResponseEntity<String> updateCompany(@RequestBody Map<String,String> request,@PathVariable Long id){
        return entrepriseS.updateCompany(request,id);
    }
}


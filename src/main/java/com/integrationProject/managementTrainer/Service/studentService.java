package com.integrationProject.managementTrainer.Service;

import com.integrationProject.managementTrainer.Model.Admin;
import com.integrationProject.managementTrainer.Model.Entreprise;
import com.integrationProject.managementTrainer.Model.Student;
import com.integrationProject.managementTrainer.Repo.adminRepo;
import com.integrationProject.managementTrainer.Repo.entrepriseRepo;
import com.integrationProject.managementTrainer.Repo.studentRepo;
import com.integrationProject.managementTrainer.util.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class studentService {
    private final entrepriseRepo entrepriseR;
    private final adminRepo adminR;

    private final studentRepo studentR;
    private final Email email;
    public void registerStudent(Student student) {
        // Vous pouvez ajouter ici la logique pour vérifier si l'étudiant existe déjà, etc.
        studentR.save(student);
    }

    public boolean validateStudent(String username, String password) {
        // Vous pouvez ajouter ici la logique pour vérifier si le nom d'utilisateur et le mot de passe sont corrects.
        // Par exemple, vous pouvez appeler une méthode du repository pour vérifier dans la base de données.
        // Pour cet exemple, supposons simplement que l'étudiant est valide si le nom d'utilisateur et le mot de passe correspondent.
        Optional<Student> student = studentR.findByEmailAndPassword(username, password);
        System.out.println(student);
        return student.isPresent();
    }

    public List<Student> findAllStudent() {
        return studentR.findAll();
    }

    public ResponseEntity<String> deleteStudent(Long id) {

        Optional<Student> optionalStudent = studentR.findById(id);

        if (optionalStudent .isPresent()) {

            studentR.deleteById(id);
            return new ResponseEntity<>("Student Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Student Not Found ", HttpStatus.OK);
    }

    public ResponseEntity<String> forgetPassword(Map<String, String> request) {
        try {

            Optional<Student> optionalStudent = studentR.findByEmail(request.get("email"));
            Optional<Entreprise> optionalEntreprise = entrepriseR.findByEmail(request.get("email"));
            Optional<Admin> optionalAdmin = adminR.findByEmail(request.get("email"));


            if (optionalStudent.isPresent() ) {
                Student student = optionalStudent.get();

                String temporyPAssword = generateRandomPassword();
                student.setPassword(temporyPAssword);
                studentR.save(student);
                email.forgotPassword(student.getEmail(), "Credential by Intern Management Platform", temporyPAssword);
                return new ResponseEntity<>("Please Check Your Email", HttpStatus.OK);
            }
            if (optionalEntreprise.isPresent() ) {
                Entreprise entreprise = optionalEntreprise.get();

                String temporyPAssword = generateRandomPassword();
                entreprise.setPassword(temporyPAssword);
                entrepriseR.save(entreprise);
                email.forgotPassword(entreprise.getEmail(), "Credential by Intern Management Platform", temporyPAssword);
                return new ResponseEntity<>("Please Check Your Email", HttpStatus.OK);
            }    if (optionalAdmin.isPresent() ) {
                Admin admin = optionalAdmin.get();

                String temporyPAssword = generateRandomPassword();
                admin.setPassword(temporyPAssword);
                adminR.save(admin);
                email.forgotPassword(admin.getEmail(), "Credential by Intern Management Platform", temporyPAssword);
                return new ResponseEntity<>("Please Check Your Email", HttpStatus.OK);
            }

            return new ResponseEntity<>("User Not Found Or email Is Empty", HttpStatus.NOT_FOUND);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Internal Problem ", HttpStatus.INTERNAL_SERVER_ERROR);

    }
    private String generateRandomPassword() {
        // Define the characters that can be used in the temporary password
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        int passwordLength = 10; // Adjust the length as needed

        StringBuilder randomPassword = new StringBuilder();

        // Generate a random password using allowed characters
        Random random = new Random();
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            randomPassword.append(allowedChars.charAt(randomIndex));
        }

        return randomPassword.toString();
    }

    public ResponseEntity<Student> getStudentByStudentId(Long id) {

        return ResponseEntity.ok(studentR.findById(id).get());
    }

    public ResponseEntity<String> updateStudent(Map<String, String> request, Long id) {
        Optional<Student> optionalStudent=studentR.findById(id);
        Student student=optionalStudent.get();
        if(request.get("email").equals("") ){
            return ResponseEntity.ok("Please set all The Input");
        }
        student.setEmail(request.get("email"));
        student.setFirstName(request.get("firstName"));
        student.setLastName(request.get("lastName"));
        student.setPhoneNumber(request.get("phoneNumber"));
        studentR.save(student);
        return ResponseEntity.ok("Student Updated Successfully ");
    }


}




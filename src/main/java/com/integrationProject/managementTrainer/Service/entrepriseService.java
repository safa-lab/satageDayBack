package com.integrationProject.managementTrainer.Service;

import com.integrationProject.managementTrainer.Model.Entreprise;
import com.integrationProject.managementTrainer.Model.Student;
import com.integrationProject.managementTrainer.Repo.entrepriseRepo;
import com.integrationProject.managementTrainer.Repo.studentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class entrepriseService {
    private final entrepriseRepo entrepriseR;
    public void registerEntreprise(Entreprise entreprise) {
        // Vous pouvez ajouter ici la logique pour vérifier si l'étudiant existe déjà, etc.

        entrepriseR.save(entreprise);
    }

    public boolean validateEntreprise(String username, String password) {
        // Vous pouvez ajouter ici la logique pour vérifier si le nom d'utilisateur et le mot de passe sont corrects.
        // Par exemple, vous pouvez appeler une méthode du repository pour vérifier dans la base de données.
        // Pour cet exemple, supposons simplement que l'étudiant est valide si le nom d'utilisateur et le mot de passe correspondent.
        Optional<Entreprise> entreprise = entrepriseR.findByEmailAndPassword(username, password);
        return entreprise.isPresent();
    }

    public List<Entreprise> findAllStudent() {
        return  entrepriseR.findAll();
    }

    public ResponseEntity<String> deleteCompany(Long id) {
        {
            Optional<Entreprise> optionalCompany = entrepriseR.findById(id);

            if (optionalCompany .isPresent()) {

                entrepriseR.deleteById(id);
                return new ResponseEntity<>("company Deleted successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("company Not Found ", HttpStatus.OK);
        }
    }


    public ResponseEntity<Entreprise> getCompanyBycompanyId(Long id) {
        return ResponseEntity.ok(entrepriseR.findById(id).get());
    }

    public ResponseEntity<String> updateCompany(Map<String, String> request, Long id) {

        Optional<Entreprise> optionalEntreprise=entrepriseR.findById(id);
        Entreprise entreprise=optionalEntreprise.get();
        if(request.get("email").equals("") ){
            return ResponseEntity.ok("Please set all The Input");
        }
        entreprise.setEmail(request.get("email"));
        entreprise.setName(request.get("name"));
        entreprise.setPhoneNumber(request.get("phoneNumber"));
        entrepriseR.save(entreprise);
        return ResponseEntity.ok("Company Updated Successfully ");
    }
}

package com.integrationProject.managementTrainer.Service;

import com.integrationProject.managementTrainer.Model.Admin;
import com.integrationProject.managementTrainer.Model.Entreprise;
import com.integrationProject.managementTrainer.Repo.adminRepo;
import com.integrationProject.managementTrainer.dto.AdminDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class adminService {

    private final adminRepo adminR;


    public ResponseEntity<AdminDto> loginAdmin(Admin admin) {
        Optional<Admin> optionalAdmin = adminR.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
        if(optionalAdmin.isPresent()){
            AdminDto adminDto=new AdminDto();

            adminDto.setEmail(optionalAdmin.get().getEmail());
            adminDto.setName(optionalAdmin.get().getName());
            return ResponseEntity.ok(adminDto);
        }

        return ResponseEntity.internalServerError().build();
    }
}

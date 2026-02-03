package com.cg.repository;
 
import com.cg.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUserUsername(String username);
}
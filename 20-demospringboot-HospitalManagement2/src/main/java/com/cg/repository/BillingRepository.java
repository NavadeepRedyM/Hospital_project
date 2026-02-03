package com.cg.repository;
 
import com.cg.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
 
public interface BillingRepository extends JpaRepository<Billing, Long> {
    List<Billing> findByPatientPatientId(Long patientId);
}